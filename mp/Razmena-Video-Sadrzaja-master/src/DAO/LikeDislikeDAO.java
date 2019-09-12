package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Comment;
import model.User;
import model.Video;





public class CommentDAO {
	public static ArrayList<Comment> getComments(int videosId){
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Comment> comments = new ArrayList<Comment>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM comment WHERE video = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videosId);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String content = rset.getString(index++);
				Date d= rset.getDate(index++);
				int owner = rset.getInt(index++);
				int videoId=rset.getInt(index++);
				boolean deleted =rset.getBoolean(index++);
				int likeNumber=rset.getInt(index++);
				int dislikeNumber=rset.getInt(index++);
				User u = UserDAO.getById(owner);
				Video video=VideoDAO.getVideoById(videoId);
				if(u == null || video == null) {
					continue;
				}
				else {
					Comment c=new Comment(id, content, u, video,d,deleted,likeNumber,dislikeNumber);
					comments.add(c);
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
		return comments;
	}
	public static ArrayList<Comment> getCommentsByOwner(int ownerId){
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Comment> comments = new ArrayList<Comment>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM comment WHERE owner = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ownerId);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String content = rset.getString(index++);
				Date d= rset.getDate(index++);
				int owner = rset.getInt(index++);
				int videoId=rset.getInt(index++);
				boolean deleted =rset.getBoolean(index++);
				int likeNumber=rset.getInt(index++);
				int dislikeNumber=rset.getInt(index++);
				User u = UserDAO.getById(owner);
				Video video=VideoDAO.getVideoById(videoId);
				if(u == null || video == null) {
					continue;
				}
				else {
					Comment c=new Comment(id, content, u, video,d,deleted,likeNumber,dislikeNumber);
					comments.add(c);
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
		return comments;
	}
	public static Comment getCommentById(int id) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM comment WHERE id = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 2;
				String content = rset.getString(index++);
				Date d= rset.getDate(index++);
				int owner = rset.getInt(index++);
				int videoId=rset.getInt(index++);
				boolean deleted=rset.getBoolean(index++);
				int likeNumber=rset.getInt(index++);
				int dislikeNumber=rset.getInt(index++);
				User u = UserDAO.getById(owner);
				Video video=VideoDAO.getVideoById(videoId);
				
				return new Comment(id, content,u, video,d,deleted,likeNumber,dislikeNumber);
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
	public static ArrayList<Comment> orderComments(int videosId,String columnName,String ascDes) {
		
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Comment> comments = new ArrayList<Comment>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM comment WHERE video = ? AND deleted = ? ORDER BY "+ columnName + " " + ascDes ;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videosId);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String content = rset.getString(index++);
				 Date d= rset.getDate(index++);
				int owner = rset.getInt(index++);
				int videoId=rset.getInt(index++);
				boolean deleted =rset.getBoolean(index++);
				int likeNumber=rset.getInt(index++);
				int dislikeNumber=rset.getInt(index++);
				User u = UserDAO.getById(owner);
				Video video=VideoDAO.getVideoById(videoId);
				
				if(u == null || video == null) {
					continue;
				}
				else {
					Comment c=new Comment(id, content, u, video,d,deleted,likeNumber,dislikeNumber);
					comments.add(c);
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
		return comments;
	}
	
	public static boolean addComment(Comment comment) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO comment (id, content,  owner, video,dateCom, deleted) VALUES (?, ?, ?, ?, ?, ? )";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, comment.getId());
			pstmt.setString(index++, comment.getContent());
			pstmt.setInt(index++, comment.getOwner().getId());
			pstmt.setInt(index++, comment.getVideo().getId());
			Date myDate=comment.getDate();
			java.sql.Date date=new java.sql.Date(myDate.getTime());
			pstmt.setDate(index++, date );
			pstmt.setBoolean(index++, comment.isDeleted());
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
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

	public static boolean updateComment(Comment comment) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE comment SET  deleted = ?, dateCom = ?, content = ?,likeNumber = ?,dislikeNumber = ? WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, comment.isDeleted());
			java.sql.Date date=new java.sql.Date(comment.getDate().getTime());
			pstmt.setDate(2, date);
			pstmt.setString(3, comment.getContent());
			pstmt.setInt(4, comment.getLikeNumber());
			pstmt.setInt(5, comment.getDislikeNumber());
			pstmt.setLong(6, comment.getId());
		
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
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
	public static int getCommentId() {
		Connection conn = ConnectionMenager.getConnection();
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM comment";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
		
			if (rset.next()) {
				id=rset.getInt(1);
				
			}
			id++;
			return id;
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
		return 0;
	}
	
	
}
