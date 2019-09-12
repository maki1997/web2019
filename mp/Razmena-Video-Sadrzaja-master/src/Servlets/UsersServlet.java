package Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.SubscribersDAO;
import DAO.UserDAO;
import model.User;



/**
 * Servlet implementation class SubscribeServlet
 */
public class SubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubscribeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//un subscribe
		String idUser=request.getParameter("channel");
		String subs=request.getParameter("subscriber");
		String status="failuer";
		
		User a=UserDAO.getByUserName(idUser);
		User b=UserDAO.getByUserName(subs);
		
		if(a != null && b != null) {
			SubscribersDAO.Delete(a.getId(),b.getId());
			status="success";
		}
		
		int subNumber=SubscribersDAO.getSubscribersNumber(a.getId());
		
		Map<String, Object> data = new HashMap<>();
		data.put("status",status);
		data.put("subN",subNumber);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//subscribe
		String idUser=request.getParameter("channel");
		String subs=request.getParameter("subscriber");
		String status="failuer";
		
		User a=UserDAO.getByUserName(idUser);
		User b=UserDAO.getByUserName(subs);
		if(a != null && b != null) {
			SubscribersDAO.Add(a.getId(),b.getId());
			status="success";
		}
		
		int subNumber=SubscribersDAO.getSubscribersNumber(a.getId());
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("status",status);
		data.put("subN",subNumber);	
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

}
