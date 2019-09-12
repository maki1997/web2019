package model;

import java.util.Date;



public class Video {
	
	public enum Visibility {
		
		PUBLIC,
		UNLISTED,
		PRIVATE
	};
	
	private int id;
	private String url;
	private String name;
	private String thumbnail;
	private String description;
	private Visibility visibility;
	private boolean blocked;
	private int previews;
	private Date date;
	private User owner;
	private boolean LDVisible;
	private boolean allowComments;
	private boolean allowRating;
	private boolean deleted;
	private int numberOfLikes;
	private int numberOfDislikes;
	
	public Video() {
		super();
	}
	
	

	


	public Video(String url, String name, String thumbnail, String description, Visibility visibility, boolean blocked,
			int previews, Date date, User owner, boolean lDVisible, boolean allowComments, boolean allowRating,
			boolean deleted, int numberOfLikes, int numberOfDislikes) {
		super();
		this.url = url;
		this.name = name;
		this.thumbnail = thumbnail;
		this.description = description;
		this.visibility = visibility;
		this.blocked = blocked;
		this.previews = previews;
		this.date = date;
		this.owner = owner;
		LDVisible = lDVisible;
		this.allowComments = allowComments;
		this.allowRating = allowRating;
		this.deleted = deleted;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
	}






	public Video(int id, String url, String name, String thumbnail, String description, Visibility visibility,
			boolean blocked, int previews, Date date, User owner, boolean lDVisible, boolean allowComments,
			boolean allowRating, boolean deleted, int numberOfLikes, int numberOfDislikes) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
		this.thumbnail = thumbnail;
		this.description = description;
		this.visibility = visibility;
		this.blocked = blocked;
		this.previews = previews;
		this.date = date;
		this.owner = owner;
		LDVisible = lDVisible;
		this.allowComments = allowComments;
		this.allowRating = allowRating;
		this.deleted = deleted;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
	}






	public int getNumberOfLikes() {
		return numberOfLikes;
	}






	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}






	public int getNumberOfDislikes() {
		return numberOfDislikes;
	}






	public void setNumberOfDislikes(int numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public int getPreviews() {
		return previews;
	}

	public void setPreviews(int previews) {
		this.previews = previews;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isLDVisible() {
		return LDVisible;
	}

	public void setLDVisible(boolean lDVisible) {
		LDVisible = lDVisible;
	}



	public boolean isAllowComments() {
		return allowComments;
	}



	public void setAllowComments(boolean allowComments) {
		this.allowComments = allowComments;
	}



	public boolean isAllowRating() {
		return allowRating;
	}



	public void setAllowRating(boolean allowRating) {
		this.allowRating = allowRating;
	}



	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
