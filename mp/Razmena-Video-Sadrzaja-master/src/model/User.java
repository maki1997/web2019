package model;

import java.util.Date;

public class Comment {

	
	private int id;
	private String content;
	private User owner;
	private Video video;
	private Date date;
	private boolean deleted;
	private int likeNumber;
	private int dislikeNumber;
	public Comment() {
		super();
	}
	
	public Comment(String content, User owner, Video video, Date date, boolean deleted, int likeNumber,
			int dislikeNumber) {
		super();
		this.content = content;
		this.owner = owner;
		this.video = video;
		this.date = date;
		this.deleted = deleted;
		this.likeNumber = likeNumber;
		this.dislikeNumber = dislikeNumber;
	}

	public Comment(int id, String content, User owner, Video video, Date date, boolean deleted, int likeNumber,
			int dislikeNumber) {
		super();
		this.id = id;
		this.content = content;
		this.owner = owner;
		this.video = video;
		this.date = date;
		this.deleted = deleted;
		this.likeNumber = likeNumber;
		this.dislikeNumber = dislikeNumber;
	}

	public int getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}

	public int getDislikeNumber() {
		return dislikeNumber;
	}

	public void setDislikeNumber(int dislikeNumber) {
		this.dislikeNumber = dislikeNumber;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
}
