package servlets;

import java.util.List;

import javax.servlet.http.HttpServlet;

import dao.FlightDAO;
import dao.ReservationDAO;
import dao.UserDAO;
import model.Flight;
import model.Reservation;
import model.User;

public class ReservationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ReservationServlet() {
		super();
	}
	
	@Override
	public void init() {
		testAdd();
		testTakenSeats();
	}
	
	
	
	private void testAdd() {
		System.out.print("testing add method on reservation servlet...");
		Flight startFlight = FlightDAO.getFlightById(2);
		Flight endFlight = FlightDAO.getFlightById(1);
		int startFlightSeat = 20;
		int endFlightSeat = 20;
		User passenger = UserDAO.getById(1);
		String firstname = "mark";
		String lastname = "jones";
		
		ReservationDAO.add(startFlight, endFlight, startFlightSeat, endFlightSeat, passenger, firstname, lastname);
		System.out.println(" OKAY");
	}
	
	
	private void testUpdate() {
		Reservation reservation = ReservationDAO.getById(1);
		reservation.setPassengerFirstname("marko");
		reservation.setPassengerLastname("markovic");
		
		System.out.print("testing update method on reservation servlet...");
		ReservationDAO.update(reservation);
		System.out.println(" OKAY");
	}
	
	private void testTakenSeats() {
		List<Integer> seats = FlightDAO.getTakenSeats(1);
		for (Integer seat : seats) 
			System.out.println(seat);
	}
}