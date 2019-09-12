package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Airport;

public class AirportDAO {
	
	public static ArrayList<Airport> getAllAirports() {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Airport> airports = new ArrayList<Airport>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM airports WHERE deleted = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1,false);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				Integer Id = rset.getInt(index++);
				String name = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);

				Airport airport = new Airport(Id,name,deleted);
				airports.add(airport);
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
		return airports;
	}

}
