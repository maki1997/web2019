package model;

import java.util.Date;

public class LikeDislike {

	private int id;
	private boolean like;
	private Date date;
	private Video video;
	private Comment comment;
	private User owner;
	
	
	public LikeDislike() {
		super();
	}
	
	public LikeDislike(int id, boolean like, Date date, Video video, Comment comment,User owner) {
		super();
		this.id = id;
		this.like = like;
		this.date = date;
		this.video = video;
		this.comment = comment;
		this.owner = owner;
	}
	public LikeDislike( boolean like, Date date, Video video, Comment comment,User owner) {
		super();
		
		this.like = like;
		this.date = date;
		this.video = video;
		this.comment = comment;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
}
