package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.User;
import model.Video;
import model.Video.Visibility;




public class VideoDAO {
	
	public static ArrayList<Video> getUserVideoId(int userID) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE owner = ? AND deleted = ? ORDER BY dateUrl DESC";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userID);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> getUserPublicVideoId(int userID) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE owner = ? AND deleted = ? AND visibility = ?  ORDER BY dateUrl DESC";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userID);
			pstmt.setBoolean(2, false);
			pstmt.setString(3, "PUBLIC");
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> allVideo() {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE (visibility = ? OR visibility = ?)  AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setString(2, "PRIVATE");
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> publicVideo() {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE (visibility = ?)  AND deleted = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static Video getVideoById(int id) {
		Connection conn = ConnectionMenager.getConnection();
		

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 2;
				
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
				return v;
				}
				
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}
	public static boolean updateVideo(Video video) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE videos SET description = ?, visibility = ?, allowComment = ?, allowRating = ?, blocked = ?, deleted = ?,previews = ?, likeNumber = ?, dislikeNumber = ?  WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, video.getDescription());
			pstmt.setString(index++, video.getVisibility().toString());
			pstmt.setBoolean(index++, video.isAllowComments());
			pstmt.setBoolean(index++, video.isAllowRating());
			pstmt.setBoolean(index++, video.isBlocked());
			pstmt.setBoolean(index++, video.isDeleted());
			pstmt.setInt(index++, video.getPreviews());
			pstmt.setInt(index++, video.getNumberOfLikes());
			pstmt.setInt(index++, video.getNumberOfDislikes());
			pstmt.setInt(index++, video.getId());

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}
	public static boolean addVideo(Video video) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO videos(url,thumbnail,name,description,visibility,blocked,previews,dateUrl,owner,likesVis,allowRating,allowComment,deleted,likeNumber,dislikeNumber)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, video.getUrl());
			pstmt.setString(index++, video.getThumbnail());
			pstmt.setString(index++, video.getName());
			pstmt.setString(index++, video.getDescription());
			pstmt.setString(index++, video.getVisibility().toString());
			pstmt.setBoolean(index++, video.isBlocked());
			pstmt.setInt(index++, video.getPreviews());
			java.sql.Date date=new java.sql.Date(video.getDate().getTime());
			pstmt.setDate(index++,date);
			pstmt.setInt(index++, video.getOwner().getId());
			pstmt.setBoolean(index++, video.isLDVisible());
			pstmt.setBoolean(index++, video.isAllowRating());
			pstmt.setBoolean(index++, video.isAllowComments());
			pstmt.setBoolean(index++, video.isDeleted());
			pstmt.setInt(index++, video.getNumberOfLikes());
			pstmt.setInt(index++, video.getNumberOfDislikes());
			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}
	public static ArrayList<Video> OrderAllUserVideo(int idOwner,String column,String ascDesc) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE owner = ? AND deleted = ? ORDER BY "+column+" "+ascDesc;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idOwner);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	
	public static ArrayList<Video> OrderPublicUserVideo(int idOwner,String column,String ascDesc) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE visibility = ? AND owner = ? AND deleted = ? ORDER BY "+column+" "+ascDesc;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setInt(2, idOwner);
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> OrderAllVideo(String column,String ascDesc) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE (visibility = ? OR visibility = ?)  AND deleted = ? ORDER BY "+column+" "+ascDesc;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setString(2, "PRIVATE");
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> OrderPublicVideo(String column,String ascDesc) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE  visibility = ?  AND deleted = ? ORDER BY "+column+" "+ascDesc;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> FilterPublicVideo(String param) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos AS v INNER JOIN users AS u ON v.owner = u.id WHERE  v.visibility = ?  AND v.deleted = ? AND u.deleted = ? AND ( v.name LIKE ? or v.previews LIKE ? or v.dateUrl LIKE ? or u.username LIKE ?) ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setBoolean(2, false);
			pstmt.setBoolean(3, false);
			pstmt.setString(4, param);
			pstmt.setString(5, param);
			pstmt.setString(6, param);
			pstmt.setString(7, param);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> FilterAllVideo(String param) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos AS v INNER JOIN users AS u ON v.owner = u.id WHERE  (v.visibility = ? OR v.visibility = ?) AND v.deleted = ? AND u.deleted = ? AND ( v.name LIKE ? or v.previews LIKE ? or v.dateUrl LIKE ? or u.username LIKE ?) ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setString(2, "PRIVATE");
			pstmt.setBoolean(3, false);
			pstmt.setBoolean(4, false);
			pstmt.setString(5, param);
			pstmt.setString(6, param);
			pstmt.setString(7, param);
			pstmt.setString(8, param);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> SearchPublicVideo(String param1,String param2,String param3) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT DISTINCT v.* FROM videos AS v INNER JOIN users AS u ON v.owner = u.id LEFT OUTER JOIN comment AS c  on c.video = v.id   WHERE  v.visibility = ?  AND v.deleted = ? AND u.deleted = ? AND c.deleted = ? AND (v.name LIKE ? or u.name LIKE ? or c.content LIKE ?) ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setBoolean(2, false);
			pstmt.setBoolean(3, false);
			pstmt.setBoolean(4, false);
			pstmt.setString(5, param1);
			pstmt.setString(6, param2);
			pstmt.setString(7, param3);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
	public static ArrayList<Video> SearchAllVideo(String param1,String param2,String param3) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT DISTINCT v.* FROM videos AS v INNER JOIN users AS u ON v.owner = u.id LEFT OUTER JOIN comment AS c  on c.video = v.id   WHERE  v.visibility = ?  AND v.visibility = ?  AND v.deleted = ? AND u.deleted = ? AND c.deleted = ? AND (v.name LIKE ? or u.name LIKE ? or c.content LIKE ?) ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setString(2, "PRIVATE");
			pstmt.setBoolean(3, false);
			pstmt.setBoolean(4, false);
			pstmt.setBoolean(5, false);
			pstmt.setString(6, param1);
			pstmt.setString(7, param2);
			pstmt.setString(8, param3);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String url = rset.getString(index++);
				String name = rset.getString(index++);
				String thumbnail = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				int previews = rset.getInt(index++);
				Date date = rset.getDate(index++);
				int user = rset.getInt(index++);
				boolean likesVis=rset.getBoolean(index++);
				boolean allowRainting = rset.getBoolean(index++);
				boolean allowComment = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				User u = UserDAO.getById(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, url, thumbnail, name, description, visibility, blocked,
						previews,date, u, likesVis, allowComment, allowRainting,deleted,likeNumber,dislikeNumber);
			
				videos.add(v);
				}
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return videos;
	}
}
