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
public class QuestionType extends AbstractEntity {
	
	@Indexed(unique = true)
	@JsonProperty("Code")
	private String code;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Name")
	private String description;
	
	public QuestionType(String code, String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
	}
	public QuestionType() {
		
	}
	
	

}
