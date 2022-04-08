package team2.api.mobile.gplx.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class TrafficSign {
	
	@Id
	private String id;
	private String name;
	private String description;
	private String photo;
	public TrafficSign(String name, String description, String photo) {
		super();
		this.name = name;
		this.description = description;
		this.photo = photo;
	}

	
}
