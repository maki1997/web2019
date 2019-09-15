package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Airport;
import model.Flight;

public class FlightDAO {
	
		public static ArrayList<Flight> getFlights(){
			Connection conn = ConnectionManager.getConnection();
			ArrayList<Flight> flights = new ArrayList<Flight>();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT * FROM flights WHERE deleted = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setBoolean(1, false);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					int index = 1;
					int id = rset.getInt(index++);
					String flightNumber = rset.getString(index++);
					Date startDate= rset.getDate(index++);
					Date endDate= rset.getDate(index++);
					int startAirport = rset.getInt(index++);
					int endAirport = rset.getInt(index++);
					int numberOfSeats=rset.getInt(index++);
					double ticketPrice=rset.getDouble(index++);
					boolean deleted =rset.getBoolean(index++);
					
					Airport a1 = AirportDAO.getById(startAirport);
					Airport a2 =AirportDAO.getById(endAirport);
					if(a1 == null || a2 == null) {
						continue;
					}
					else {
						Flight f=new Flight(id, flightNumber, startDate, endDate, a1, a2, numberOfSeats, ticketPrice, deleted);
						flights.add(f);
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
			return flights;
		}
		
		public static boolean Update(Flight flight ) {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = null;
			try {
				String query = "UPDATE flights SET  flightNumber = ?, startAirport = ?, endAirport = ?,startDate = ?,endDate = ?, numberOfSeats = ?, ticketPrice = ?, deleted = ?  WHERE id=?; ";

				pstmt = conn.prepareStatement(query);
				int index = 1;
				pstmt.setString(index++, flight.getFlightNumber());
				pstmt.setInt(index++, flight.getStartAirport().getId());
				pstmt.setInt(index++, flight.getEndAirport().getId());
				Date sDate=flight.getStartDate();
				java.sql.Date startDate=new java.sql.Date(sDate.getTime());
				Date eDate=flight.getEndDate();
				java.sql.Date endDate=new java.sql.Date(eDate.getTime());
				pstmt.setDate(index++, startDate );
				pstmt.setDate(index++, endDate );
				System.out.println(flight.getStartAirport().getId());
				
				pstmt.setInt(index++, flight.getNumberOfSeats());
				pstmt.setDouble(index++,flight.getTicketPrice());
				pstmt.setBoolean(index++, flight.isDeleted());
				pstmt.setInt(index++, flight.getId());
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
		
		
		public static Flight getFlightById(int id) {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT * FROM flights WHERE id = ? AND deleted = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, id);
				pstmt.setBoolean(2, false);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					int index = 2;
					String flightNumber = rset.getString(index++);
					Date startDate= rset.getDate(index++);
					Date endDate= rset.getDate(index++);
					int startAirport = rset.getInt(index++);
					int endAirport = rset.getInt(index++);
					int numberOfSeats=rset.getInt(index++);
					double ticketPrice=rset.getDouble(index++);
					boolean deleted =rset.getBoolean(index++);
					
					Airport a1 = AirportDAO.getById(startAirport);
					Airport a2 =AirportDAO.getById(endAirport);
					
					return new Flight(id, flightNumber, startDate, endDate, a1, a2, numberOfSeats, ticketPrice, deleted);
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
		
		
		public static boolean addFlight(Flight flight) {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			try {
				String query = "INSERT INTO flights (id, flightNumber,  startDate, endDate ,startAirport, endAirport, numberOfSeats, ticketPrice, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )";

				pstmt = conn.prepareStatement(query);
				int index = 1;
				pstmt.setInt(index++, flight.getId());
				pstmt.setString(index++, flight.getFlightNumber());
				Date sDate=flight.getStartDate();
				java.sql.Date startDate=new java.sql.Date(sDate.getTime());
				Date eDate=flight.getEndDate();
				java.sql.Date endDate=new java.sql.Date(eDate.getTime());
				pstmt.setDate(index++, startDate );
				pstmt.setDate(index++, endDate );
				pstmt.setInt(index++, flight.getStartAirport().getId());
				pstmt.setInt(index++, flight.getEndAirport().getId());
				pstmt.setInt(index++, flight.getNumberOfSeats());
				pstmt.setDouble(index++,flight.getTicketPrice());
				pstmt.setBoolean(index++, flight.isDeleted());
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
		
		public static int getMaxFlightId() {
			Connection conn = ConnectionManager.getConnection();
			int id=0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT MAX(id) FROM flights";
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
		
		
		public static ArrayList<Flight> Filter(String param) {
			
			
			
			ArrayList<Flight> flights=new ArrayList<Flight>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Connection conn = ConnectionManager.getConnection();
			try {
				String query = "SELECT * FROM flights WHERE deleted = ? and flightNumber LIKE ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setBoolean(1, false);
				pstmt.setString(2, param);
				rset = pstmt.executeQuery();
				
				while (rset.next()) {
					int index = 1;
					int id = rset.getInt(index++);
					String flightNumber = rset.getString(index++);
					Date startDate= rset.getDate(index++);
					Date endDate= rset.getDate(index++);
					int startAirport = rset.getInt(index++);
					int endAirport = rset.getInt(index++);
					int numberOfSeats=rset.getInt(index++);
					double ticketPrice=rset.getDouble(index++);
					boolean deleted =rset.getBoolean(index++);
					
					Airport a1 = AirportDAO.getById(startAirport);
					Airport a2 =AirportDAO.getById(endAirport);
					if(a1 == null || a2 == null) {
						continue;
					}
					else {
						Flight f=new Flight(id, flightNumber, startDate, endDate, a1, a2, numberOfSeats, ticketPrice, deleted);
						flights.add(f);
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
			return flights;
		}
		
		
		public static ArrayList<Flight> OrderBy(String column, String ascDesc) {
			ArrayList<Flight> flights=new ArrayList<Flight>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Connection conn = ConnectionManager.getConnection();
			try {
				String query = "SELECT * FROM flights WHERE deleted = ? ORDER BY "+column+" "+ascDesc;
				pstmt = conn.prepareStatement(query);
				pstmt.setBoolean(1, false);
				rset = pstmt.executeQuery();
				
				while (rset.next()) {
					int index = 1;
					int id = rset.getInt(index++);
					String flightNumber = rset.getString(index++);
					Date startDate= rset.getDate(index++);
					Date endDate= rset.getDate(index++);
					int startAirport = rset.getInt(index++);
					int endAirport = rset.getInt(index++);
					int numberOfSeats=rset.getInt(index++);
					double ticketPrice=rset.getDouble(index++);
					boolean deleted =rset.getBoolean(index++);
					
					Airport a1 = AirportDAO.getById(startAirport);
					Airport a2 =AirportDAO.getById(endAirport);
					if(a1 == null || a2 == null) {
						continue;
					}
					else {
						Flight f=new Flight(id, flightNumber, startDate, endDate, a1, a2, numberOfSeats, ticketPrice, deleted);
						flights.add(f);
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
			return flights;
		}
		
		
		
		
		
	public static List<Integer> getTakenSeats(int flightId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Integer> takenSeats = new ArrayList<>();
		
		try {
			String query = "SELECT startFlightSeat from reservations WHERE startFlight = ? AND deleted = 0 " +
		                   "UNION " + 
					       "SELECT endFlightSeat from reservations WHERE endFlight = ? AND deleted = 0;";
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(index++, flightId); 
			pstmt.setInt(index++, flightId);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				takenSeats.add(rset.getInt(1));
			}
			
			return takenSeats;
			
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

}
