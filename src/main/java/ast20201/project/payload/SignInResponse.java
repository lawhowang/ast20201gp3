package ast20201.project.payload;

public class SignInResponse {
	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";

	public SignInResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.setRefreshToken(refreshToken);
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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}