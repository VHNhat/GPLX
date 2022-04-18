package team2.api.mobile.gplx.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import team2.api.mobile.gplx.commondata.model.AbstractEntity;

@Data
@Document
public class Role extends AbstractEntity {
	@JsonProperty("RoleName")
	private String roleName;
	@JsonProperty("Description")
	private String description;

	
}
