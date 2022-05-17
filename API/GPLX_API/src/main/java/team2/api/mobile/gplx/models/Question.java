package team2.api.mobile.gplx.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import team2.api.mobile.gplx.commondata.model.AbstractEntity;

@Document
@Getter
@Setter
public class Question extends AbstractEntity {
	
	@JsonProperty("Index")
	private int index;
	
	@JsonProperty("Query")
	private String query;
	
	@JsonProperty("IsTop50")
	private boolean isTop50;
	
	@JsonProperty("Photo")
	private String photo;
	
	@JsonProperty("LicenseId")
	private String licenseId;
	
	@JsonProperty("QuestionId")
	private String questionTypeId;
	
	public Question(int index, String query, boolean isTop50, String photo, String licenseId, String questionTypeId) {
		super();
		this.index = index;
		this.query = query;
		this.isTop50 = isTop50;
		this.photo = photo;
		this.licenseId = licenseId;
		this.questionTypeId = questionTypeId;
	}

	public Question() {
		
	}
	
	

}
