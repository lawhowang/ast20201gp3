package ast20201.project.payload;

public class SignUpResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	
	public SignUpResponse(String accessToken) {
        this.setAccessToken(accessToken);
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
