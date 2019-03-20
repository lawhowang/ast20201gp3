package ast20201.project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ast20201.project.domain.User;
import ast20201.project.payload.ErrorResponse;
import ast20201.project.payload.SignInResponse;
import ast20201.project.payload.SignUpResponse;
import ast20201.project.service.UserService;
import ast20201.project.util.JwtTokenProvider;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
    JwtTokenProvider tokenProvider;

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@Valid @RequestBody User user) {
		// Check if duplicated in the database
		List<String> errors = new ArrayList<String>();
		if (userService.checkUsernameDuplicated(user.getUsername())) {
			errors.add("Username already exists");
		}
		if (userService.checkEmailDuplicated(user.getEmail())) {
			errors.add("Email already exists");
		}
		if (errors.size() > 0) {
			return new ResponseEntity<Object>(new ErrorResponse(errors.toArray(new String[errors.size()])), HttpStatus.BAD_REQUEST);
		}
		// Registration
		userService.addUser(user);
		
		// Authentication
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsernameOrEmail(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        return new ResponseEntity<Object>(new SignUpResponse(user.getUsername(), jwt), HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody User user) {
		System.out.println(user.getUsernameOrEmail());
		System.out.println(user.getPassword());
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsernameOrEmail(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new SignInResponse(jwt));
    }
}
