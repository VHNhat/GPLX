package team2.api.mobile.gplx.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import team2.api.mobile.gplx.commondata.model.AbstractEntity;

@Document
@Getter
@Setter
public class LicenseType extends AbstractEntity {
	
	@Indexed(unique = true)
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Status")
	private Status status;
	
	@JsonProperty("Description")
	private String description;
	
	public LicenseType(String name, Status status, String description) {
		super();
		this.name = name;
		this.status = status;
		this.description = description;
	}
	public LicenseType() {

	}
	
	
}
