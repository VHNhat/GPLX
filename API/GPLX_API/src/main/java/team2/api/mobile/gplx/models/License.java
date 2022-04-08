package team2.api.mobile.gplx.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import team2.api.mobile.gplx.commondata.model.AbstractEntity;

@Document
@Getter
@Setter
public class License extends AbstractEntity {
	
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Status")
	private Status status;
	@JsonProperty("Description")
	private String description;
	
}
