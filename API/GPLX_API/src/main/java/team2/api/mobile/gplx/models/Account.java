package team2.api.mobile.gplx.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import team2.api.mobile.gplx.commondata.model.AbstractEntity;

@Data
@Document
public class Account extends AbstractEntity {
	@JsonProperty("Username")
	private String username;
	@JsonProperty("Password")
	private String password;
	@JsonProperty("Email")
	private String email;
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("Avatar")
	private String avatar;
	@JsonProperty("Status")
	private AccountStatus status;
	
	private String roleId;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
	
	
}
