package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.LikeDislike;







public class LikeDislikeDAO {
	public static int getVideosLikeNumber(int videoId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM likeDislikeVideo JOIN likes on likeDislikeVideo.likeId = likes.id WHERE likeDislike= ? AND likeDislikeVideo.videoId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, videoId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
					int likeNumber =rset.getInt(1);
					
					return likeNumber;
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
		return 0;
	}
	public static int getCommentLikeNumber(int commentId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM likeDislikeComment JOIN likes on likeDislikeComment.likeId = likes.id WHERE likeDislike= ? AND likeDislikeComment.commentId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, commentId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
					int likeNumber =rset.getInt(1);
					
					return likeNumber;
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
		return 0;
	}
	
	public static int getVideosDislikeNumber(int videoId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM likeDislikeVideo JOIN likes on likeDislikeVideo.likeId = likes.id WHERE likeDislike= ? AND likeDislikeVideo.videoId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, videoId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
					int likeNumber =rset.getInt(1);
					
					return likeNumber;
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
		return 0;
	}
	public static int getCommentDislikeNumber(int commentId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM likeDislikeComment JOIN likes on likeDislikeComment.likeId = likes.id WHERE likeDislike= ? AND likeDislikeComment.commentId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, commentId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
					int likeNumber =rset.getInt(1);
					
					return likeNumber;
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
		return 0;
	}
	public static LikeDislike getVideosByOwner(int videoId,int ownerUserId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM likeDislikeVideo JOIN likes on likeDislikeVideo.likeId = likes.id WHERE likeDislikeVideo.videoId = ? AND likes.owner = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videoId);
			pstmt.setInt(2, ownerUserId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				int videosId=rset.getInt(index++);
				int likeId=rset.getInt(index++);
				boolean isLike=rset.getBoolean(index++);
				Date d=rset.getDate(index++);
				int owner=rset.getInt(index++);
				
				
				return  new LikeDislike(likeId, isLike, d, VideoDAO.getVideoById(videosId), null, UserDAO.getById(owner));
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
	
	public static LikeDislike getCommentByOwner(int commentId,int ownerId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM likeDislikeComment JOIN likes on likeDislikeComment.likeId = likes.id WHERE likeDislikeComment.commentId = ? AND likes.owner = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentId);
			pstmt.setInt(2, ownerId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				int commenId=rset.getInt(index++);
				int likeId=rset.getInt(index++);
				boolean isLike=rset.getBoolean(index++);
				Date d=rset.getDate(index++);
				int owner=rset.getInt(index++);
				
				return  new LikeDislike(likeId, isLike, d, null, CommentDAO.getCommentById(commenId), UserDAO.getById(owner));
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
	public static boolean addVideoLikeDislike(int l, int videoId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query="INSERT INTO likeDislikeVideo(likeId,videoId) VALUES(?, ?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, l);
			pstmt.setInt(2, videoId);
			 return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
			try {conn.rollback();} catch (SQLException ex1) {ex1.printStackTrace();}
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}

			try {conn.setAutoCommit(true);} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	public static boolean addCommentLikeDislike(int likeId, int commentId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query="INSERT INTO likeDislikeComment(likeId,commentId) VALUES(?, ?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, likeId);
			pstmt.setInt(2, commentId);
			 return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
			try {conn.rollback();} catch (SQLException ex1) {ex1.printStackTrace();}
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}

			try {conn.setAutoCommit(true);} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	public static boolean addLikes(LikeDislike likeDislike) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO likes(id,likeDislike,likeDate,owner) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, likeDislike.getId());
			pstmt.setBoolean(2, likeDislike.isLike());
			java.sql.Date date=new java.sql.Date(likeDislike.getDate().getTime());
			pstmt.setDate(3, date);
			pstmt.setInt(4, likeDislike.getOwner().getId());
			return pstmt.executeUpdate() == 1;


		}  catch (Exception ex) {
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
	public static boolean updateLike(LikeDislike l) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE likes SET likeDislike = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, l.isLike());
			pstmt.setLong(2, l.getId());
			
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
	public static int getLikeId() {
		Connection conn = ConnectionMenager.getConnection();
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM likes";
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
