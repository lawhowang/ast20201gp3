/*
 * This class only handle format error of incomming request params
 * References
 * http://www.springboottutorial.com/spring-boot-validation-for-rest-services
 * https://g00glen00b.be/validating-the-input-of-your-rest-api-with-spring/
 * https://stackoverflow.com/questions/41481933/spring-boot-rest-validation-error-response
 */

package ast20201.project.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ast20201.project.model.FieldErrorResponse;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		FieldErrorResponse er = new FieldErrorResponse();
		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for (FieldError fe : fieldErrors) {
			er.addErrors(fe.getField(), fe.getDefaultMessage());
		}
		return new ResponseEntity<Object>(er.hasErrors() ? er : ex, headers, status);
	}
}