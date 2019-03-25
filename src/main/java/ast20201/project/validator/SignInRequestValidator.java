package ast20201.project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ast20201.project.model.SignUpRequest;

@Component
public class SignInRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

	}
}