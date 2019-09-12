package model;

import java.util.Date;
import java.util.List;



public class User {

	public enum Role {
		USER, ADMIN
	};

	private int id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String description;
	private Date date;
	private Role role;
	private boolean blocked;
	private List<User> subscribers;
	private List<LikeDislike> videoLikes;
	private List<LikeDislike> commentLikes;
	private boolean deleted;
	
	public User() {
		super();
	}

	public User(Integer id, String username, String password, String name, String surname, String email,
			String description, Date date, Role role, boolean blocked, List<User> subscribers,
			List<LikeDislike> videoLikes, List<LikeDislike> commentLikes,boolean deleted) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.description = description;
		this.date = date;
		this.role = role;
		this.blocked = blocked;
		this.subscribers = subscribers;
		this.videoLikes = videoLikes;
		this.commentLikes = commentLikes;
		this.deleted=deleted;
	}
	public User( String username, String password, String name, String surname, String email,
			 Date date, Role role, boolean blocked, List<User> subscribers,
			List<LikeDislike> videoLikes, List<LikeDislike> commentLikes,boolean deleted) {
		super();
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		
		this.date = date;
		this.role = role;
		this.blocked = blocked;
		this.subscribers = subscribers;
		this.videoLikes = videoLikes;
		this.commentLikes = commentLikes;
		this.deleted=deleted;
	}

	public User(Integer id, String username, String password, String name, String surname, String email,
			String description, Date date, Role role, boolean blocked,boolean deleted) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.description = description;
		this.date = date;
		this.role = role;
		this.blocked = blocked;
		this.deleted=deleted;
	}
	public User( String username, String password, String name, String surname, String email,
			String description, Date date, Role role, boolean blocked,boolean deleted) {
		super();
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.description = description;
		this.date = date;
		this.role = role;
		this.blocked = blocked;
		this.deleted=deleted;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public List<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}

	public List<LikeDislike> getVideoLikes() {
		return videoLikes;
	}

	public void setVideoLikes(List<LikeDislike> videoLikes) {
		this.videoLikes = videoLikes;
	}

	public List<LikeDislike> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(List<LikeDislike> commentLikes) {
		this.commentLikes = commentLikes;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
