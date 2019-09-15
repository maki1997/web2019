package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class UsersServlet
 */
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//admin page, dobija se lista usera u apps
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			ArrayList<User> users = UserDAO.getAllUsers();

			Map<String, Object> data = new HashMap<>();
			data.put("user", loggedInUser);
			data.put("users", users);
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
		
		switch (status) {
		case "delete":
			try {
				HttpSession session = request.getSession();
				User loggedInUser = (User) session.getAttribute("loggedInUser");
				String username = request.getParameter("userName");
				
				User user = UserDAO.getByUserName(username);
				user.setDeleted(true);
				UserDAO.Update(user);
				
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
		case "filter":
			String statu = "success";
			ArrayList<User> usersF=null;
			
			try {
				String param=request.getParameter("param");
				usersF=UserDAO.Filter(param);
				
			} catch (Exception e) {status="faliuer";}
			
			Map<String, Object> dataF = new HashMap<>();
			dataF.put("stat", statu);
			dataF.put("users", usersF);
			ObjectMapper mapperF = new ObjectMapper();
			String jsonDataF = mapperF.writeValueAsString(dataF);
			response.setContentType("application/json");
			response.getWriter().write(jsonDataF);	
			
			break;
			
		case "order":
			String stat = "success";
			ArrayList<User> users=null;
			
			try {
				String column=request.getParameter("column");
				String ascDesc=request.getParameter("ascDesc");
				users=UserDAO.OrderBy(column, ascDesc);
				
			} catch (Exception e) {status="faliuer";}
			
			Map<String, Object> data = new HashMap<>();
			data.put("stat", stat);
			data.put("users", users);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
			break;
		case "block":
			try {
				HttpSession session = request.getSession();
				User loggedInUser = (User) session.getAttribute("loggedInUser");
				String username = request.getParameter("userName");
				
				User user = UserDAO.getByUserName(username);
				user.setBlocked(true);
				UserDAO.Update(user);
				
				/*ArrayList<Ticket> tickets =TicketsDAO.getUsersTickets(user.getId());
				for(Ticket t: tickets) {
					t.setDeleted(true);
					TicketsDAO.updateTicket(t);
				}*/
				
				
				Map<String, Object> dataq = new HashMap<>();
				dataq.put("status", "success");
				dataq.put("loggedUser", loggedInUser);
				ObjectMapper mapperq = new ObjectMapper();
				String jsonDataq = mapperq.writeValueAsString(dataq);
				response.setContentType("application/json");
				response.getWriter().write(jsonDataq);

			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		}
	}
}
