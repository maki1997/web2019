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
import model.Airport;
import model.Flight;
import model.User;

/**
 * Servlet implementation class FlightsServlet
 */
public class FlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightsServlet() {
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
			ArrayList<Flight> flights = FlightDAO.getFlights();
			Map<String, Object> data = new HashMap<>();
			data.put("user", loggedInUser);
			data.put("flights", flights);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		System.out.println(status);
		switch (status) {
		case "delete":
			try {
				HttpSession session = request.getSession();
				User loggedInUser = (User) session.getAttribute("loggedInUser");
				if (loggedInUser == null || !loggedInUser.getRole().equals(User.Role.ADMIN)) {
					response.setStatus(400);
					return;
				}
				
				int idF = Integer.parseInt(request.getParameter("idF"));
				Flight f = FlightDAO.getFlightById(idF);
				if (f == null) {
					response.setStatus(400);
					return;
				}
				
				f.setDeleted(true);
				FlightDAO.Update(f);
				
				/*ArrayList<Ticket> tickets =TicketsDAO.getUsersTickets(user.getId());
				for(Ticket t: tickets) {
					t.setDeleted(true);
					TicketsDAO.updateTicket(t);
				}*/
				
				
				Map<String, Object> data = new HashMap<>();
				data.put("status", "success");
				data.put("loggedUser", loggedInUser);
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(data);
				
				response.setContentType("application/json");
				response.getWriter().write(jsonData);

			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "edit":
			try {
				HttpSession session = request.getSession();
				User loggedInUser = (User) session.getAttribute("loggedInUser");
				if (loggedInUser == null || !loggedInUser.getRole().equals(User.Role.ADMIN)) {
					response.setStatus(400);
					return;
				}
				
				int idF = Integer.parseInt(request.getParameter("idF"));
				Flight f = FlightDAO.getFlightById(idF);
				if (f == null) {
					response.setStatus(400);
					return;
				}
				
				System.out.println(f.getFlightNumber());
				String endA = request.getParameter("endA");
				System.out.println("naziv krajnjeg aerodroma"+endA);
				Airport eA = AirportDAO.getByName(endA);
				System.out.println(eA.getName());
				
				f.setEndAirport(eA);
				Date date = null;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endD"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				f.setEndDate(date);
				System.out.println(date);
				Integer nos = Integer.parseInt(request.getParameter("numofs"));
				
				System.out.println(nos);
				Double tp = Double.parseDouble(request.getParameter("p"));
				
				if (nos == null || tp == null) {
					response.setStatus(400);
					return;
				} else if (nos > 0 && tp > 0.0) {
					response.setStatus(400);
					return;
				}
				
				f.setNumberOfSeats(nos);
				f.setTicketPrice(tp);
				System.out.println(tp);
				FlightDAO.Update(f);
				
				/*ArrayList<Ticket> tickets =TicketsDAO.getUsersTickets(user.getId());
				for(Ticket t: tickets) {
					t.setDeleted(true);
					TicketsDAO.updateTicket(t);
				}*/
				
				
				Map<String, Object> data = new HashMap<>();
				data.put("status", "success");
				data.put("loggedUser", loggedInUser);
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(data);
				
				response.setContentType("application/json");
				response.getWriter().write(jsonData);

			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
			break;
		case "add":
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null || !loggedInUser.getRole().equals(User.Role.ADMIN)) {
				response.setStatus(400);
				return;
			}

			String flight_number = request.getParameter("flightNumber");
			String start = request.getParameter("sAirport");
			String end = request.getParameter("eAirport");
			Integer nos = Integer.parseInt(request.getParameter("numberOfSeats"));
			Double price = Double.parseDouble(request.getParameter("price"));
			Airport a1 = AirportDAO.getByName(start);
			Airport a2 = AirportDAO.getByName(end);
			
			if (flight_number == null || start == null || end == null || nos == null || price == null || a1 == null || a2 == null) {
				response.setStatus(400);
				return;
			}
			
			
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("sDate"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}  
			Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("eDate"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			int id = FlightDAO.getMaxFlightId();
			Flight f = new Flight(id,flight_number,date,date1, a1, a2, nos, price, false);
			FlightDAO.addFlight(f);
			
			Map<String, Object> data3 = new HashMap<>();
			data3.put("status", "success");
			data3.put("f", f);
			ObjectMapper mapper3 = new ObjectMapper();
			String jsonData3 = mapper3.writeValueAsString(data3);
			System.out.println(jsonData3);
			response.setContentType("application/json");
			response.getWriter().write(jsonData3);
			break;
			
		case "filter":
			String statu = "success";
			ArrayList<Flight> flightsF=null;
			
			try {
				String param=request.getParameter("param");
				flightsF=FlightDAO.Filter(param);
				
			} catch (Exception e) {status="faliuer";}
			
			Map<String, Object> dataF = new HashMap<>();
			dataF.put("stat", statu);
			dataF.put("flights", flightsF);
			ObjectMapper mapperF = new ObjectMapper();
			String jsonDataF = mapperF.writeValueAsString(dataF);
			response.setContentType("application/json");
			response.getWriter().write(jsonDataF);	
			
			break;
			
		case "order":
			String stat = "success";
			ArrayList<Flight> flights=null;
			
			try {
				String column=request.getParameter("column");
				String ascDesc=request.getParameter("ascDesc");
				if (column == null || ascDesc == null) {
					response.setStatus(400);
					return;
				}
				
				flights=FlightDAO.OrderBy(column, ascDesc);
				
			} catch (Exception e) {status="faliuer";}
			
			Map<String, Object> data = new HashMap<>();
			data.put("stat", stat);
			data.put("flights", flights);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
			break;
		case "search":
			searchByDestination(request, response);
			break;
		case "getTakenSeats":
			getTakenSeats(request, response);
			break;
		}
	}
	
	private void searchByDestination(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		
		String startAirport = (String) request.getParameter("startAirport");
		String endAirport = (String) request.getParameter("endAirport");
		List<Flight> flights = FlightDAO.searchByAirports(startAirport, endAirport);
		
		Map<String, Object> data = new HashMap<>();
		data.put("loggedUser", loggedUser);
		data.put("flights", flights);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);	
	}
	
	private void getTakenSeats(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");

		Integer flightId = Integer.parseInt(request.getParameter("flightId"));
		Integer seatNum = Integer.parseInt(request.getParameter("seatNum"));
		List<Integer> takenSeats = FlightDAO.getTakenSeats(flightId);
		
		Map<String, Object> data = new HashMap<>();
		data.put("loggedUser", loggedUser);
		data.put("available", takenSeats.contains(seatNum));
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);	
	}
	
}
