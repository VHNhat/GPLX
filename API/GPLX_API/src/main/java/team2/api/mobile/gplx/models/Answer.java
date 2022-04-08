package team2.api.mobile.gplx.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Answer {
	
	@Id
	private String id;
	private boolean result;
	private int index;
	public Answer(boolean result, int index) {
		super();
		this.result = result;
		this.index = index;
	}

	
}
