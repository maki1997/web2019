package DAO;



import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.User;



public class SubscribersDAO {
	
	public static int findSubscribed(int userName, int subscriber) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM subscribers WHERE  idUser= ? AND subscriber = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userName);
			pstmt.setInt(2, subscriber);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				int subs = rset.getInt(index);
				return subs;

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
	public static int getSubscribersNumber(int userId) {
		
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT Count(*) FROM subscribers WHERE idUser = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				int subs = rset.getInt(index);
				return subs;
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
	public static ArrayList<User> subscribedOn(int userId) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<User> subscribed = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT idUser  FROM subscribers WHERE subscriber = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				int user = rset.getInt(index);

				User u = UserDAO.getById(user);
				subscribed.add(u);
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
		return subscribed;
	}
	public static boolean Add(int idUser, int subs) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO subscribers(idUser,subscriber) VALUES(?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idUser);
			pstmt.setInt(2, subs);
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

	public static boolean Delete(int idUser, int subs) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM subscribers WHERE idUser =? AND subscriber= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idUser);
			pstmt.setInt(2, subs);
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
	
}
