package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Flight;
import model.Reservation;
import model.User;

public class ReservationDAO {
	
	public static Reservation getById(int flightId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM reservations WHERE id = ? AND deleted = 0;";
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(index++, flightId);
			rset = pstmt.executeQuery();
			
			index = 1;
			if (rset.next()) {
				int flight_id = rset.getInt(index++);
				Flight startFlight = FlightDAO.getFlightById(rset.getInt(index++));
				Flight endFlight = FlightDAO.getFlightById(rset.getInt(index++));
				int startFlightSeat = rset.getInt(index++);
				int endFlightSeat = rset.getInt(index++);
				Date reservationDate = rset.getDate(index++);
				Date ticketSaleDate = rset.getDate(index++);
				User passenger = UserDAO.getById(rset.getInt(index++));
				String firstname = rset.getString(index++);
				String lastname = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				Reservation reservation = new Reservation(flight_id, startFlight, endFlight, startFlightSeat, endFlightSeat, reservationDate, ticketSaleDate, passenger, firstname, lastname, deleted);
				return reservation;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close(); 
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	public static List<Reservation> getByUser(int userId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reservation> reservations = new ArrayList<>();

		try {
			String query = "SELECT * FROM reservations WHERE passenger = ? AND deleted = 0;";
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(index++, userId);
			rset = pstmt.executeQuery();
			
			
			while (rset.next()) {
				index = 1;
				int flight_id = rset.getInt(index++);
				Flight startFlight = FlightDAO.getFlightById(rset.getInt(index++));
				Flight endFlight = FlightDAO.getFlightById(rset.getInt(index++));
				int startFlightSeat = rset.getInt(index++);
				int endFlightSeat = rset.getInt(index++);
				Date reservationDate = rset.getDate(index++);
				Date ticketSaleDate = rset.getDate(index++);
				User passenger = UserDAO.getById(rset.getInt(index++));
				String firstname = rset.getString(index++);
				String lastname = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				Reservation reservation = new Reservation(flight_id, startFlight, endFlight, startFlightSeat, endFlightSeat, reservationDate, ticketSaleDate, passenger, firstname, lastname, deleted);
				reservations.add(reservation);
			}
			return reservations;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close(); 
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	
	public static List<Reservation> getByStartFlight(int flightId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reservation> reservations = new ArrayList<>();

		try {
			String query = "SELECT * FROM reservations WHERE startFlight = ? AND deleted = 0 " +
					       "UNION " +
					       "SELECT * FROM reservations WHERE endFlight = ? AND deleted = 0";

			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(index++, flightId);
			pstmt.setInt(index++, flightId);
			rset = pstmt.executeQuery();
			
			
			while (rset.next()) {
				index = 1;
				int flight_id = rset.getInt(index++);
				Flight startFlight = FlightDAO.getFlightById(rset.getInt(index++));
				Flight endFlight = FlightDAO.getFlightById(rset.getInt(index++));
				int startFlightSeat = rset.getInt(index++);
				int endFlightSeat = rset.getInt(index++);
				Date reservationDate = rset.getDate(index++);
				Date ticketSaleDate = rset.getDate(index++);
				User passenger = UserDAO.getById(rset.getInt(index++));
				String firstname = rset.getString(index++);
				String lastname = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				Reservation reservation = new Reservation(flight_id, startFlight, endFlight, startFlightSeat, endFlightSeat, reservationDate, ticketSaleDate, passenger, firstname, lastname, deleted);
				reservations.add(reservation);
			}
			return reservations;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close(); 
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	

	public static void add(Reservation r) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO reservations (startFlight, endFlight, startFlightSeat, endFlightSeat, reservationDate, ticketSaleDate, passenger, passengerFirstName, passengerLastName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(index++, r.getStartFlight().getId());
			pstmt.setInt(index++, r.getEndFlight().getId());
			pstmt.setInt(index++, r.getStartFlightSeat()); 
			pstmt.setInt(index++, r.getEndFlightSeat());
			pstmt.setDate(index++, new Date(r.getStartFlight().getStartDate().getTime()));
			pstmt.setDate(index++, new Date(new java.util.Date().getTime()));
			pstmt.setInt(index++, r.getPassenger().getId());
			pstmt.setString(index++, r.getPassengerFirstname());
			pstmt.setString(index++, r.getPassengerLastname());
			pstmt.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close(); 
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void update(Reservation reservation) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE reservations SET startFlightSeat = ?, endFlightSeat = ?, ticketSaleDate = ?, passengerFirstName = ?, passengerLastName = ?, deleted = ? WHERE id = ? AND deleted = 0;";
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(index++, reservation.getStartFlightSeat()); 
			pstmt.setInt(index++, reservation.getEndFlightSeat());
			pstmt.setDate(index++, new Date(reservation.getTicketSaleDate().getTime()));
			pstmt.setString(index++, reservation.getPassengerFirstname());
			pstmt.setString(index++, reservation.getPassengerLastname());
			pstmt.setBoolean(index++, reservation.isDeleted());
			pstmt.setInt(index++, reservation.getId());
			pstmt.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close(); 
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

}
