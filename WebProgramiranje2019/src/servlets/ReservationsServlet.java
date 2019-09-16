package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.AirportDAO;
import dao.FlightDAO;
import dao.ReservationDAO;
import model.Airport;
import model.Flight;
import model.Reservation;
import model.User;

/**
 * Servlet implementation class ReservationsServlet
 */
public class ReservationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		System.out.println(status);
		switch (status) {
		
		case "getRezervacijaPoUseru":
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			response.setStatus(400);
			return;
		}
		Integer userId = loggedInUser.getId();
		List<Reservation> res = ReservationDAO.getByUser(userId);
		Map<String, Object> data = new HashMap<>();
		data.put("user", loggedInUser);
		data.put("reservations", res);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		break;
		
		case "getRezervacijaPoLetu":
			Integer flightId = Integer.parseInt(request.getParameter("reservationId"));
			System.out.println(flightId);
			List<Reservation> res1 = ReservationDAO.getByStartFlight(flightId);
			Map<String, Object> data1 = new HashMap<>();
			data1.put("reservationsByFlight", res1);
			ObjectMapper mapper1 = new ObjectMapper();
			String jsonData1 = mapper1.writeValueAsString(data1);
			response.setContentType("application/json");
			response.getWriter().write(jsonData1);
			break;
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		System.out.println(status);
		switch (status) {
		
		case "add":
			User loggedUser = (User) request.getSession().getAttribute("loggedInUser");
			// check if user is logged in
			if (loggedUser == null) {
				response.setStatus(400);
				return;
			}
			
			Integer startFlightId = Integer.parseInt(request.getParameter("startFlight"));	
			Integer endFlightId = Integer.parseInt(request.getParameter("endFlight"));
			Integer startFlightSeat = Integer.parseInt(request.getParameter("startFlightSeat"));
			Integer endFlightSeat = Integer.parseInt(request.getParameter("endFlightSeat"));
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			System.out.println(startFlightId + endFlightId);
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
			break;
		case "delete":
			User loggedUser1 = (User) request.getSession().getAttribute("loggedInUser");
			// check if user is logged in
			if (loggedUser1 == null ) {
				response.setStatus(400);
				return;
			}
			
			// check if id != null
			Integer reservationId = Integer.parseInt(request.getParameter("idR"));
			Reservation reservation = ReservationDAO.getById(reservationId);
			// check if reservation != null
			if (reservation == null) {
				response.setStatus(400);
				return;
			}
			
			// check if user is authorized to execute add reservation
			if (loggedUser1.getId() != reservation.getPassenger().getId()) {
				response.setStatus(400);
				return;
			}
			
			reservation.setDeleted(true);
			ReservationDAO.update(reservation);
			
			Map<String, Object> data1 = new HashMap<>();
			data1.put("user", loggedUser1);
			ObjectMapper mapper1 = new ObjectMapper();
			String jsonData1 = mapper1.writeValueAsString(data1);
			response.setContentType("application/json");
			response.getWriter().write(jsonData1);
			response.setStatus(200);
			break;
		}
	}
	
}
