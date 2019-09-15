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
import model.User.Role;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//podaci za ulazak u user page
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			String username=request.getParameter("userName");
			//User owner = UserDAO.getByUserName(username);
			//ArrayList<Reservation> reservations = null;
			String status="visiter";
			
			
			if(loggedInUser != null) {
				status="logedUser";
				
				
				}
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", status);
			data.put("user", loggedInUser);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			}catch (Exception e) {
				// TODO: handle exception
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status=request.getParameter("status");
		if(status.equals("edit")) {
			String password=request.getParameter("password");
			String role=request.getParameter("role");
			String username=request.getParameter("userName");
			String un=request.getParameter("un");
			Role r;
			if(role.equals("USER")) {
				r=Role.USER;
			}
			else {
				r=Role.ADMIN;
			}
			System.out.println(username);
			System.out.println("pre get by username");
			User u =UserDAO.getByUserName(un);
			
			u.setUsername(username);
			u.setPassword(password);
			System.out.println(u.getUsername());
			u.setRole(r);
			
			UserDAO.Update(u);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			
		}
	}

}
