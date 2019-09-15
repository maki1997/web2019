package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.User;
import model.User.Role;

public class UserDAO {
	
	public static ArrayList<User> getAllUsers() {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<User> users = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM users WHERE deleted = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1,false);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				Integer Id = rset.getInt(index++);
				String username = rset.getString(index++);
				String password = rset.getString(index++);
				Date registrationDate = rset.getDate(index++);
				Role role = Role.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);

				User user = new User(Id, username, password, registrationDate, role, blocked,deleted);
				users.add(user);
			}
			
			
		}catch(SQLException ex){
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		finally {
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
		return users;
	}
	
	public static User getByUserName(String username) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM users WHERE userName = ? AND deleted = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 1;
				Integer Id = rset.getInt(index++);
				String userName = rset.getString(index++);
				String password = rset.getString(index++);
				Date registrationDate = rset.getDate(index++);
				Role role = Role.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);

				User user = new User(Id, userName, password, registrationDate, role, blocked,deleted);
				return user;
			}
			
			
		}catch(SQLException ex){
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		finally {
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

	
	
	public static User getById(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM users WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 1;
				Integer Id = rset.getInt(index++);
				String userName = rset.getString(index++);
				String password = rset.getString(index++);
				Date registrationDate = rset.getDate(index++);
				Role role = Role.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);

				User user = new User(Id, userName, password, registrationDate, role, blocked,deleted);
				return user;
			}
			
			
		}catch(SQLException ex){
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		finally {
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
	public static boolean Add(User user ) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO users (userName,password,registrationDate,role,blocked,deleted) VALUES (?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			
			
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			pstmt.setDate(index++, new java.sql.Date(user.getRegistrationDate().getTime()));
			pstmt.setString(index++, user.getRole().toString());
			pstmt.setBoolean(index++, user.isBlocked());
			pstmt.setBoolean(index++, user.isDeleted());
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	public static boolean Update(User user ) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE users SET  userName = ?, password = ?,  role = ?, blocked = ?,deleted = ?  WHERE id=?; ";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getRole().toString());
			pstmt.setBoolean(index++, user.isBlocked());
			pstmt.setBoolean(index++, user.isDeleted());
			pstmt.setInt(index++, user.getId());
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	public static boolean Delete(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "delete from users where id = ?; ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate() == 1;
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	public static ArrayList<User> OrderBy(String column, String ascDesc) {
		ArrayList<User> users=new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Connection conn = ConnectionManager.getConnection();
		try {
			String query = "SELECT * FROM users WHERE deleted = ? ORDER BY "+column+" "+ascDesc;
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int index = 1;
				Integer Id = rset.getInt(index++);
				String username = rset.getString(index++);
				String password = rset.getString(index++);
				Date date = rset.getDate(index++);
				Role role = Role.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				User user = new User(Id, username, password, date, role, blocked,deleted);
				 
				users.add(user);

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
		return users;
	}
	public static ArrayList<User> Filter(String param) {
		
		
		
		ArrayList<User> users=new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Connection conn = ConnectionManager.getConnection();
		try {
			String query = "SELECT * FROM users WHERE deleted = ? and (userName LIKE ?  or role LIKE ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setString(2, param);
			pstmt.setString(3, param);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int index = 1;
				Integer Id = rset.getInt(index++);
				String username = rset.getString(index++);
				String password = rset.getString(index++);
				Date date = rset.getDate(index++);
				Role role = Role.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				User user = new User(Id, username, password, date, role, blocked,deleted);
				 
				users.add(user);

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
		return users;
	}
	
}

