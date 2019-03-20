package ast20201.project.payload;

public class SignUpResponse {
	private String username;
	private String accessToken;
	private String tokenType = "Bearer";
	
	public SignUpResponse(String username, String accessToken) {
        this.setUsername(username);
        this.setAccessToken(accessToken);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
