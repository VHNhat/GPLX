package team2.api.mobile.gplx.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class QuestionType {
	
	@Id
	private String id;
	private String name;
	private String description;
	public QuestionType(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	

}
