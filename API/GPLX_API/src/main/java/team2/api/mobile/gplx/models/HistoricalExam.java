/**
 * 
 */
package team2.api.mobile.gplx.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import team2.api.mobile.gplx.commondata.model.AbstractEntity;

/**
 * @author Malik
 *
 */

@Document
@Getter
@Setter
public class HistoricalExam extends AbstractEntity {
	@JsonProperty("userid")
	private String userId;

	@JsonProperty("setid")
	private String setId;
	
	@JsonProperty("date")
	private String examDate;
	
	@JsonProperty("correct")
	private int correctNumber;
	
	@JsonProperty("total")
	private int total;

	public HistoricalExam() {
		super();
	}

	public HistoricalExam(String userId, String setId, String examDate, int correctNumber, int total) {
		super();
		this.userId = userId;
		this.setId = setId;
		this.examDate = examDate;
		this.correctNumber = correctNumber;
		this.total = total;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public int getCorrectNumber() {
		return correctNumber;
	}

	public void setCorrectNumber(int correctNumber) {
		this.correctNumber = correctNumber;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
