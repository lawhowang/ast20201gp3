package ast20201.project.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Component
public class SignUpRequest {
	@NotNull(message = "Username is a required field")
	@Size(min = 6, max = 12, message = "Username length must be betweeb 6 and 12 characters")
	private String username;
	@NotNull(message = "Password is a required field")
	@Size(min = 6, max = 12, message = "Password length must be betweeb 6 and 12 characters")
	private String password;
	@NotNull(message = "Email is a required field")
	@Email(message = "Please provide a valid email address")
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getUsernameOrEmail() {
		if (this.username == null)
			return email;
		return username;
	}

	public String getPassword() {
		return password;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getHashedPassword() {
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
