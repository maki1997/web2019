package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.FlightDAO;
import dao.ReservationDAO;
import model.Flight;
import model.Reservation;
import model.User;

public class ReservationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ReservationServlet() {
		super();
	}
	
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = (String) request.getAttribute("status");
		switch(status) {
		case "addReservation":
			addReservation(request, response);
			break;
		case "confirmReservation":
			confirmReservation(request, response);
			break;
		case "cancelReservation":
			cancelReservation(request, response);
			break;
		case "updateReservation":
			updateReservation(request, response);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String status = (String) request.getAttribute("status");
		switch(status) {
		case "getByFlight":
			getReservationsByFlight(request, response);
			break;
		case "getByUser":
			//getByUser(request, response);
			break;
		default:
			break;
		}
	}
	
	
	private void addReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		// check if user is logged in
		// check if user is authorized to execute add reservation
		
		int startFlightId = (int) request.getAttribute("startFlight");	
		int endFlightId = (int) request.getAttribute("endFlight");
		int startFlightSeat = (int) request.getAttribute("startFlightSeat");
		int endFlightSeat = (int) request.getAttribute("endFlightSeat");
		String firstname = (String) request.getAttribute("firstname");
		String lastname = (String) request.getAttribute("lastname");
		// check if all parameters are non null
		
		Flight startFlight = FlightDAO.getFlightById(startFlightId);
		Flight endFlight = FlightDAO.getFlightById(endFlightId);
		// check if returned flights are non null
		
		Reservation r = new Reservation(startFlight, endFlight, startFlightSeat, endFlightSeat, loggedUser, firstname, lastname);
		ReservationDAO.add(r);
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("user", loggedUser);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		response.setStatus(200);
	}
	
	
	private void confirmReservation(HttpServletRequest request, HttpServletResponse response) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		// check if user is logged in
		// check if user is authorized to execute add reservation
		
		int reservationId = (int) request.getAttribute("reservationId");
		// check if user is owner of reservation
		
		Reservation reservation = ReservationDAO.getById(reservationId);
		reservation.setTicketSaleDate(new Date());
		ReservationDAO.update(reservation);
		response.setStatus(200);
	}
	
	
	
	private void cancelReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		// check if user is logged in
		// check if user is authorized to execute add reservation
		
		int reservationId = (int) request.getAttribute("reservationId");
		// check if id != null
		
		Reservation reservation = ReservationDAO.getById(reservationId);
		// check if reservation != null
		// check if user owns reservation
		
		reservation.setDeleted(true);
		ReservationDAO.update(reservation);
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("user", loggedUser);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		response.setStatus(200);
	}
	
	
	
	private void updateReservation(HttpServletRequest request, HttpServletResponse response) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		// check if user is logged in
		// check if user is authorized to update reservation
		
		int reservationId = (int) request.getAttribute("reservationId");
		// check if id != null
		
		Reservation reservation = ReservationDAO.getById(reservationId);
		// check if reservation != null
		// check if user owns reservation
		
		
		/** possible attributes for request: 
		 * 1) getStartFlightSeat
		 * 2) getEndFlightSeat
		 * 3) firstname
		 * 4) lastname 
		**/
	}
	
	
	private void getReservationsByFlight(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		// check if user is logged in
		// check if user is authorized to execute get by flight 
		
		int flightId = (int) request.getAttribute("flightId");
		// flightId != null
		
		
		
		List<Reservation> reservations = ReservationDAO.getByStartFlight(flightId);
		
		Map<String, Object> data = new HashMap<>();
		data.put("user", loggedUser);
		data.put("reservations", reservations);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		response.setStatus(200);
		
	}
}