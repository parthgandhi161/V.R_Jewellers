package main.customer;

public class Customer {
	
	int id;
	String cname,sname,sadd;
	int conno;
	
	public Customer(int id, String cname, String sname, String sadd, int conno) {
		super();
		this.id = id;
		this.cname = cname;
		this.sname = sname;
		this.sadd = sadd;
		this.conno = conno;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSadd() {
		return sadd;
	}
	public void setSadd(String sadd) {
		this.sadd = sadd;
	}
	public int getConno() {
		return conno;
	}
	public void setConno(int conno) {
		this.conno = conno;
	}

}
