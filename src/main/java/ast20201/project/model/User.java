/*
 * User model. The notations with message are trigger when format mismatch 
 */

package ast20201.project.model;

import ast20201.project.validation.ValidationGroup;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Component
public class User implements Serializable {
	@NotNull(groups = { ValidationGroup.EditUser.class }, message = "ID is a required field")
	private long id;

	@NotBlank(groups = { ValidationGroup.SignupUser.class,
			ValidationGroup.EditUser.class, ValidationGroup.AddUser.class }, message = "Username is a required field")
	@Size(groups = { ValidationGroup.SignupUser.class,
			ValidationGroup.EditUser.class, ValidationGroup.AddUser.class }, min = 6, max = 12, message = "Username length must be between 6 and 12 characters")
	@Pattern(groups = { ValidationGroup.SignupUser.class,
			ValidationGroup.EditUser.class, ValidationGroup.AddUser.class }, regexp = "^[a-zA-Z0-9_]*$", message = "Username can only contain letters and numbers")
	private String username;

	@NotBlank(groups = { ValidationGroup.SigninUser.class,
			ValidationGroup.SignupUser.class, ValidationGroup.AddUser.class }, message = "Password is a required field")
	@Size(groups = { ValidationGroup.SigninUser.class,
			ValidationGroup.SignupUser.class, ValidationGroup.AddUser.class }, min = 6, max = 12, message = "Password length must be between 6 and 12 characters")
	@Pattern(groups = {
			ValidationGroup.EditUser.class }, regexp = "^$|^.{6,12}$", message = "Either leave password field empty or set a valid password with length 6 ~ 12")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@NotBlank(groups = { ValidationGroup.SignupUser.class,
			ValidationGroup.EditUser.class, ValidationGroup.AddUser.class }, message = "Email is a required field")
	@Email(groups = { ValidationGroup.SignupUser.class,
			ValidationGroup.EditUser.class, ValidationGroup.AddUser.class }, message = "Please provide a valid email address")
	private String email;

	@NotBlank(groups = { ValidationGroup.EditUser.class, ValidationGroup.AddUser.class }, message = "Role is a required field")
	private String role;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Timestamp create_date;

	private Timestamp last_login_date;
	private BigDecimal credits;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		if (usernameOrEmail.contains("@"))
			this.email = usernameOrEmail;
		else
			this.username = usernameOrEmail;
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(groups = { ValidationGroup.SigninUser.class }, message = "Username/Email is a required field")
	public String getUsernameOrEmail() {
		if (this.username == null)
			return email;
		return username;
	}

	public String getPassword() {
		return password;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public Timestamp getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(Timestamp last_login_date) {
		this.last_login_date = last_login_date;
	}

    public BigDecimal getCredits() {
        return credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }

}
