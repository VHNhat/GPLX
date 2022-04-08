package team2.api.mobile.gplx.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class QuestionSet {

	@Id
	private String id;
	private String name;
	private Status status;
	private int quantity;
	private int wrongAns;
	private int rightAns;
}
