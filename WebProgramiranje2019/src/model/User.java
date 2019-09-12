package model;

import java.util.Date;
import java.util.List;

public class User {
	
	private int id;
	private String username;
	private String password;
	private Date registrationDate;
	public enum Role {
		USER, ADMIN
	};
	private Role role;
	private List<Reservation> reservations;
	


	private boolean blocked;
	private boolean deleted;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int id, String username, String password, Date registrationDate, Role role, boolean blocked, boolean deleted) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		this.blocked = blocked;
		this.deleted = deleted;
	}
	
	public User( String username, String password, Date registrationDate, Role role, boolean blocked, boolean deleted,List<Reservation> reservations) {
		super();
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		this.blocked = blocked;
		this.deleted = deleted;
		this.reservations = reservations;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public boolean isBlocked() {
		return blocked;
	}


	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
