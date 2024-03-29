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
		String status = (String) request.getParameter("status");
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
		String status = (String) request.getParameter("status");
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
		User loggedUser = (User) request.getSession().getAttribute("loggedInUser");
		// check if user is logged in
		if (loggedUser == null) {
			response.setStatus(400);
			return;
		}
		
		Integer startFlightId = Integer.parseInt( request.getParameter("startFlight"));	
		Integer endFlightId = Integer.parseInt( request.getParameter("endFlight"));
		Integer startFlightSeat = Integer.parseInt( request.getParameter("startFlightSeat"));
		Integer endFlightSeat = Integer.parseInt( request.getParameter("endFlightSeat"));
		String firstname = (String) request.getParameter("firstname");
		String lastname = (String) request.getParameter("lastname");
		// check if all parameters are non null
		
		if (startFlightId == null || endFlightId == null || startFlightSeat == null || endFlightSeat == null || firstname == null || lastname == null) {
			response.setStatus(400);
			return;
		}
		
		
		Flight startFlight = FlightDAO.getFlightById(startFlightId);
		Flight endFlight = FlightDAO.getFlightById(endFlightId);
		// check if returned flights are non null
		if (startFlight == null || endFlight == null) {
			response.setStatus(400);
			return;
		}
		
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
		User loggedUser = (User) request.getSession().getAttribute("loggedInUser");
		// check if user is logged in
		// check if user is authorized to execute add reservation
		
		Integer reservationId = Integer.parseInt( request.getParameter("reservationId"));
		// check if user is owner of reservation
		
		Reservation reservation = ReservationDAO.getById(reservationId);
		reservation.setTicketSaleDate(new Date());
		ReservationDAO.update(reservation);
		response.setStatus(200);
	}
	
	
	
	private void cancelReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User loggedUser = (User) request.getSession().getAttribute("loggedInUser");
		// check if user is logged in
		// check if user is authorized to execute add reservation
		
		Integer reservationId = Integer.parseInt( request.getParameter("reservationId"));
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
		User loggedUser = (User) request.getSession().getAttribute("loggedInUser");
		
		// check if user is logged in
		// check if user is authorized to update reservation
		
		Integer reservationId = Integer.parseInt( request.getParameter("reservationId"));
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
		
		Integer startFlightSeat = Integer.parseInt(request.getParameter("getStartFlightSeat"));
		if (startFlightSeat  != null) {
			reservation.setStartFlightSeat(startFlightSeat);
		}
		
		Integer endFlightSeat = Integer.parseInt(request.getParameter("endFlightSeat"));
		if (endFlightSeat != null) {
			reservation.setEndFlightSeat(endFlightSeat);
		}
		
		if (request.getParameter("firstname") != null) {
			reservation.setPassengerFirstname(request.getParameter("firstname"));
		}
		
		if (request.getParameter("lastname") != null) {
			reservation.setPassengerFirstname(request.getParameter("lastname"));
		}
		
		
		// UPDATE OR RETURN BAD REQUEST
		if (reservation.getTicketSaleDate() == null) {
			ReservationDAO.update(reservation);
		} else {
			response.setStatus(400);
		}
		
	}
	
	
	private void getReservationsByFlight(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User loggedUser = (User) request.getSession().getAttribute("loggedInUser");
		// check if user is logged in
		// check if user is authorized to execute get by flight 
		
		Integer flightId = Integer.parseInt(request.getParameter("flightId"));
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