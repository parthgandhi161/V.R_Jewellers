package main.product;

public class Product {
	
	int id;
	String pname;
	float dtouch;
	float dlabour;
	
	public Product(int id, String pname, float dtouch, float dlabour) {
		super();
		this.id = id;
		this.pname = pname;
		this.dtouch = dtouch;
		this.dlabour = dlabour;
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public float getDtouch() {
		return dtouch;
	}
	public void setDtouch(float dtouch) {
		this.dtouch = dtouch;
	}
	public float getDlabour() {
		return dlabour;
	}
	public void setDlabour(float dlabour) {
		this.dlabour = dlabour;
	}

}
