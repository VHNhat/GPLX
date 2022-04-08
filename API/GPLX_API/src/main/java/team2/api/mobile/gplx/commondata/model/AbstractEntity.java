package team2.api.mobile.gplx.commondata.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class AbstractEntity {
	@Id
	protected String id;
}
