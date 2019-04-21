/* 
 * References
 * https://stackoverflow.com/questions/25075683/spring-mvc-validator-annotation-custom-validation
 * https://stackoverflow.com/questions/14533488/adding-multiple-validators-using-initbinder
 */
package ast20201.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ast20201.project.model.FieldErrorResponse;
import ast20201.project.model.User;
import ast20201.project.service.UserService;
import ast20201.project.validation.ValidationGroup;

@RequestMapping(value = "/api/user")
@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getUser() {
		User user = userService.getCurrentUser();
		return ResponseEntity.ok(user);
	}

	@RequestMapping(method = RequestMethod.POST)
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

		userService.addUser(user);
		User dbUser = userService.loginUser(user);

		return ResponseEntity.ok(dbUser);
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<Object> signIn(@Validated({ ValidationGroup.SigninUser.class }) @RequestBody User user) {
		User dbUser = null;
		try {
			dbUser = userService.loginUser(user);
		} catch (UsernameNotFoundException ex) {
			FieldErrorResponse errors = new FieldErrorResponse();
			errors.addErrors("usernameOrEmail", "User not found");
			return ResponseEntity.badRequest().body(errors);
		} catch (BadCredentialsException ex) {
			FieldErrorResponse errors = new FieldErrorResponse();
			errors.addErrors("password", "Password incorrect");
			return ResponseEntity.badRequest().body(errors);
		}

		return ResponseEntity.ok(dbUser);
	}

	@RequestMapping(value = "/signout", method = RequestMethod.POST)
	public ResponseEntity<Object> signOut() {
		userService.logoutUser();
		return ResponseEntity.ok("{}");
	}
}
