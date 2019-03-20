/*
 * References
 * https://blog.csdn.net/itguangit/article/details/78960127
 * https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-2/
 */
package ast20201.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import ast20201.project.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userService));
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());
        http.cors().and().csrf().disable().authorizeRequests()
	        .antMatchers("/api/admin/**").authenticated()
	        .anyRequest().permitAll()
	        .and()
	        .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }
}
