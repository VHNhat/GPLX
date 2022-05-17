package team2.api.mobile.gplx.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import team2.api.mobile.gplx.commondata.model.AbstractEntity;

@Document
@Getter
@Setter
public class QuestionSet extends AbstractEntity {
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Status")
	private Status status;
	
	@JsonProperty("Quantity")
	private int quantity;
	
	@JsonProperty("WrongAns")
	private int wrongAns;
	
	@JsonProperty("RightAns")
	private int rightAns;
	
	public QuestionSet(String name, Status status, int quantity, int wrongAns, int rightAns) {
		super();
		this.name = name;
		this.status = status;
		this.quantity = quantity;
		this.wrongAns = wrongAns;
		this.rightAns = rightAns;
	}
	public QuestionSet() {
		
	}
	
}
