package model;

public class Airport {
	
	private int id;
	private String name;
	private boolean deleted;
	
	
	public Airport() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Airport(int id, String name, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.deleted = deleted;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	

}
