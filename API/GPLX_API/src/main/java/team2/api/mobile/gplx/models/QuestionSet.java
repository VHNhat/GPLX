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
public class QuestionSet extends AbstractEntity {
	
	@JsonProperty("Name")
	@Indexed(unique = true)
	private String name;
	
	@JsonProperty("Status")
	private boolean status;
	
	@JsonProperty("Quantity")
	private int quantity;
	
	@JsonProperty("WrongAns")
	private int wrongAns;
	
	@JsonProperty("RightAns")
	private int rightAns;
	
	public QuestionSet(String name, boolean status, int quantity, int wrongAns, int rightAns) {
		super();
		this.name = name;
		this.status = status;
		this.quantity = quantity;
		this.wrongAns = wrongAns;
		this.rightAns = rightAns;
	}
	public QuestionSet() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getWrongAns() {
		return wrongAns;
	}
	public void setWrongAns(int wrongAns) {
		this.wrongAns = wrongAns;
	}
	public int getRightAns() {
		return rightAns;
	}
	public void setRightAns(int rightAns) {
		this.rightAns = rightAns;
	}
}
