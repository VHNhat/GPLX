package team2.api.mobile.gplx.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document
public class Question {
	
	@Id
	private String id;
	private int index;
	private String query;
	private boolean isTop50;
	private String photo;
	public Question(int index, String query, boolean isTop50, String photo) {
		super();
		this.index = index;
		this.query = query;
		this.isTop50 = isTop50;
		this.photo = photo;
	}
	

}
