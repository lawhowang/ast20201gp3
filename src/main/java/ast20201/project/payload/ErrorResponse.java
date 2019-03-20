/*
 * Payload that model the error response
 */
package ast20201.project.payload;

public class ErrorResponse {
	private String[] error;
	
	public ErrorResponse(String[] error) {
		this.setError(error);
	}

	public String[] getError() {
		return error;
	}

	public void setError(String[] error) {
		this.error = error;
	}
}
