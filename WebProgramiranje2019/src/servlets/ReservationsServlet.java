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
		try {
			//lista letova
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			int userId = loggedInUser.getId();
			int flightId = Integer.parseInt(request.getParameter("reservationId"));
			System.out.println(flightId);
			List<Reservation> res = ReservationDAO.getByUser(userId);
			List<Reservation> res1 = ReservationDAO.getByStartFlight(flightId);
			Map<String, Object> data = new HashMap<>();
			data.put("user", loggedInUser);
			data.put("reservations", res);
			data.put("reservationsByFlight", res1);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
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
			// check if user is authorized to execute add reservation
			if(loggedUser == null) {
				System.out.println("user je null");
			}
			
			int startFlightId = Integer.parseInt(request.getParameter("startFlight"));	
			int endFlightId = Integer.parseInt(request.getParameter("endFlight"));
			int startFlightSeat = Integer.parseInt(request.getParameter("startFlightSeat"));
			int endFlightSeat = Integer.parseInt(request.getParameter("endFlightSeat"));
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			System.out.println(startFlightId + endFlightId);
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
			break;
		}
	}

}
