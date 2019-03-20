/*
 * User model. The notations with message are trigger when format mismatch 
 */

package ast20201.project.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class User {
  private long id;
  @NotNull(message = "Username is a required field")
  @Size(min = 6, max = 12, message = "Username length must be betweeb 6 and 12 characters")
  private String username;
  @NotNull(message = "Password is a required field")
  @Size(min = 6, max = 12, message = "Password length must be betweeb 6 and 12 characters")
  private String password;
  @NotNull(message = "Email is a required field")
  @Email(message="Please provide a valid email address")
  private String email;
  private String phone;
  private String address;
  private Timestamp create_date;
  
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
  
  public String getUsernameOrEmail() {
	if (this.username == null)
		return email;
	return username;
  }
  
  public String getPassword() {
	return password;
  }
  
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
  
  public String getPhone() {
	return phone;
  }
  
  public void setCellphone(String cellphone) {
	this.phone = cellphone;
  }
  
  public String getAddress() {
	return address;
  }
  
  public void setAddress(String address) {
	this.address = address;
  }

	public Timestamp getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
  
}