/*
 * Payload that model the error response
 */
package ast20201.project.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FieldErrorResponse {
	
	private Map<String, Set<String>> errors;

	public FieldErrorResponse() {
		errors = new HashMap<String, Set<String>>();
	}

	public Map<String, Set<String>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, Set<String>> error) {
		this.errors = error;
	}

	public void addErrors(String fieldName, String errorDetail) {
		if (errors.containsKey(fieldName)) {
			errors.get(fieldName).add(errorDetail);
		} else {
			Set<String> errorDetails = new HashSet<String>();
			errorDetails.add(errorDetail);
			errors.put(fieldName, errorDetails);
		}
	}

	public boolean hasErrors() {
		return errors.size() > 0;
	}
}
