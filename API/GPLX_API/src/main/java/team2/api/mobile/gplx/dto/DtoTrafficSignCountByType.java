package team2.api.mobile.gplx.dto;

public class DtoTrafficSignCountByType {
	private String id;
	private String code;
	private int quantity;

	public DtoTrafficSignCountByType() {
		super();
	}

	public DtoTrafficSignCountByType(String id, String code, int quantity) {
		super();
		this.id = id;
		this.code = code;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
