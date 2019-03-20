/*
 * This class only handle format error of incomming request params
 * References
 * http://www.springboottutorial.com/spring-boot-validation-for-rest-services
 * https://g00glen00b.be/validating-the-input-of-your-rest-api-with-spring/
 * https://stackoverflow.com/questions/41481933/spring-boot-rest-validation-error-response
 */

package ast20201.project.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
	    Map <String, Set<String>> errorsMap =  fieldErrors.stream().collect(
	            Collectors.groupingBy(FieldError::getField,
	                    Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())
	            )
	    );
	    return new ResponseEntity<Object>(errorsMap.isEmpty()? ex:errorsMap, headers, status);
    }
	/*@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Object>("{ \"error\":[ " + ex.getBindingResult()
			.getAllErrors().stream()
			.map(ObjectError::getDefaultMessage)
			.collect(Collectors.joining("\", \"", "\"", "\"")) + " ] }", HttpStatus.BAD_REQUEST);
	}*/
}