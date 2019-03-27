/*
 * References
 * https://blog.csdn.net/itguangit/article/details/78960127
 * https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-2/
 * https://segmentfault.com/a/1190000010434946
 * https://stackoverflow.com/questions/19500332/spring-security-and-json-authentication
 */
package ast20201.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import ast20201.project.service.UserService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new CustomAuthenticationProvider(userService));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Custom handler when not authenticated
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		// Custom handler when not enough permission
		http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());

		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().permitAll();
	}
}
