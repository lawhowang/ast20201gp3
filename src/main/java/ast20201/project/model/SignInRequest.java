package ast20201.project.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class SignInRequest {
	@NotNull(message = "Username is a required field")
	private String usernameOrEmail;
	@NotNull(message = "Password is a required field")
	private String password;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
