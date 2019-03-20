package ast20201.project.configuration;

import java.util.ArrayList;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import ast20201.project.domain.User;
import ast20201.project.service.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;
    
    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    /*
     * Veriify user by comparing md5 password stored in the database
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String usernameOrEmail = authentication.getName();
        String password = authentication.getCredentials().toString();
        String hashedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        User user;
        try {
        	user = userService.getUserByUsernameOrEmail(usernameOrEmail);
        } catch (EmptyResultDataAccessException e) {
        	throw new UsernameNotFoundException("User does not exist");
        }

        String userPassword = user.getPassword();
        
        if (!userPassword.equals(hashedPassword)) {
            throw new BadCredentialsException("Password incorrect");
        }

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("BASIC"));

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getId(), password, authorities);

        return auth;

    }


}