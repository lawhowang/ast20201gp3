package ast20201.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ast20201.project.model.SignUpRequest;
import ast20201.project.service.UserService;

@Component
public class SignUpRequestValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SignUpRequest signUpRequest = (SignUpRequest) target;
		if (userService.checkUsernameDuplicated(signUpRequest.getUsername())) {
			errors.rejectValue("username", "error.username.taken", "Username has already been taken");
		}
		if (userService.checkEmailDuplicated(signUpRequest.getEmail())) {
			errors.rejectValue("email", "error.email.taken", "Email address has already been taken");
		}
	}
}