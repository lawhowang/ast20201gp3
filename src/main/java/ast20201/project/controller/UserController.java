/* 
 * References
 * https://stackoverflow.com/questions/25075683/spring-mvc-validator-annotation-custom-validation
 * https://stackoverflow.com/questions/14533488/adding-multiple-validators-using-initbinder
 */
package ast20201.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ast20201.project.model.SignInRequest;
import ast20201.project.model.SignUpRequest;
import ast20201.project.model.User;
import ast20201.project.service.UserService;
import ast20201.project.validator.SignUpRequestValidator;

@RequestMapping(value = "/api")
@Controller
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	SignUpRequestValidator signUpRequestValidator;

	@Autowired
	SignUpRequestValidator signInRequestValidator;

	@InitBinder("signUpRequest")
	protected void initSignUpRequestBinder(WebDataBinder binder) {
		binder.addValidators(signUpRequestValidator);
	}
	
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
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
		User user = new User();
		user.setUsername(signUpRequest.getUsername());
		user.setPassword(signUpRequest.getPassword());
		user.setEmail(signUpRequest.getEmail());

		// Registration
		userService.addUser(user);

		// Authentication
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsernameOrEmail(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User dbuser = (User) authentication.getPrincipal();
		userService.updateLastLogin(dbuser.getId());
		return ResponseEntity.ok(dbuser);
	}

	@RequestMapping(value = "/user/signin", method = RequestMethod.POST)
	public ResponseEntity<Object> signIn(@Valid @RequestBody SignInRequest signInRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				signInRequest.getUsernameOrEmail(), signInRequest.getPassword()));
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public ResponseEntity<?> hello() {
		System.out.println("isAdmin");
		String json = "{ }";
		return ResponseEntity.ok(json);
	}
}
