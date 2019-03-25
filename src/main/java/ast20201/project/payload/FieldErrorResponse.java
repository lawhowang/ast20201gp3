/*
 * Payload that model the error response
 */
package ast20201.project.payload;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FieldErrorResponse {
	private Map<String, Set<String>> error;

	public FieldErrorResponse() {
		error = new HashMap<String, Set<String>>();
	}

	public Map<String, Set<String>> getError() {
		return error;
	}

	public void setError(Map<String, Set<String>> error) {
		this.error = error;
	}

	public void addError(String fieldName, String errorDetail) {
		if (error.containsKey(fieldName)) {
			error.get(fieldName).add(errorDetail);
		} else {
			Set<String> errorDetails = new HashSet<String>();
			errorDetails.add(errorDetail);
			error.put(fieldName, errorDetails);
		}
	}

	public int size() {
		return error.size();
	}
}
