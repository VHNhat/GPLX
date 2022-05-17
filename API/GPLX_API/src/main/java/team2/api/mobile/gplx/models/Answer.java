package team2.api.mobile.gplx.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import team2.api.mobile.gplx.commondata.model.AbstractEntity;


@Document
@Getter
@Setter
public class Answer extends AbstractEntity {
	
	private boolean result;
	private int index;
	private String questionId;
	public Answer(boolean result, int index, String questionId) {
		super();
		this.result = result;
		this.index = index;
		this.questionId = questionId;
	}
	public Answer() {
		
	}
	

	
}
