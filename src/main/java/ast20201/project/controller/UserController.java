/* 
 * References
 * https://stackoverflow.com/questions/25075683/spring-mvc-validator-annotation-custom-validation
 * https://stackoverflow.com/questions/14533488/adding-multiple-validators-using-initbinder
 */
package ast20201.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ast20201.project.model.FieldErrorResponse;
import ast20201.project.model.User;
import ast20201.project.service.UserService;
import ast20201.project.validation.ValidationGroup;

@RequestMapping(value = "/api")
@Controller
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = new User();
		try {
			user = (User) authentication.getPrincipal();
		} catch (Exception ex) {

		}
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@Validated({ ValidationGroup.SignupUser.class }) @RequestBody User user) {
		/* Handle validation for username and email before inserting to database */
		FieldErrorResponse errors = new FieldErrorResponse();
		if (userService.checkUsernameDuplicated(user.getUsername())) {
			errors.addErrors("username", "Username has already been taken");
		}
		if (userService.checkEmailDuplicated(user.getEmail())) {
			errors.addErrors("email", "Email address has already been taken");
		}
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors);
		}

		// Add user to database
		String plainPassword = user.getPassword();
		String hashedPassword = DigestUtils.md5DigestAsHex(plainPassword.getBytes());
		user.setPassword(hashedPassword);	// User posted password are not yet hashed, therefore hash it before inserting to db
		userService.addUser(user);

		// Authentication after registration
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsernameOrEmail(), plainPassword));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User dbuser = (User) authentication.getPrincipal();
		userService.updateLastLogin(dbuser.getId());
		return ResponseEntity.ok(dbuser);
	}

	@RequestMapping(value = "/user/signin", method = RequestMethod.POST)
	public ResponseEntity<Object> signIn(@Validated({ ValidationGroup.SigninUser.class }) @RequestBody User signin) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signin.getUsernameOrEmail(), signin.getPassword()));
		} catch (UsernameNotFoundException ex) {
			FieldErrorResponse errors = new FieldErrorResponse();
			errors.addErrors("usernameOrEmail", "User not found");
			return ResponseEntity.badRequest().body(errors);
		} catch (BadCredentialsException ex) {
			FieldErrorResponse errors = new FieldErrorResponse();
			errors.addErrors("password", "Password incorrect");
			return ResponseEntity.badRequest().body(errors);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User dbuser = (User) authentication.getPrincipal();
		userService.updateLastLogin(dbuser.getId());
		return ResponseEntity.ok(dbuser);
	}

	@RequestMapping(value = "/user/signout", method = RequestMethod.POST)
	public ResponseEntity<Object> signOut() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return ResponseEntity.ok("{}");
	}
}
