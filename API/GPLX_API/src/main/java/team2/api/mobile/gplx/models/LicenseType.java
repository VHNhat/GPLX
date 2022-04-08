package team2.api.mobile.gplx.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class LicenseType {
	
	@Id
	private String id;
	private String name;
	private Status status;
	private String description;
	public LicenseType(String name, Status status, String description) {
		super();
		this.name = name;
		this.status = status;
		this.description = description;
	}
	
	
}
