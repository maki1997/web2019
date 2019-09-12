package Servlets;

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

import DAO.CommentDAO;
import DAO.UserDAO;
import DAO.VideoDAO;
import model.Comment;
import model.User;
import model.Video;

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
		//delete,order i filter za usera u admin page
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
				
				ArrayList<Video> videos =VideoDAO.getUserVideoId(user.getId());
				for(Video v: videos) {
					v.setDeleted(true);
					VideoDAO.updateVideo(v);
				}
				
				ArrayList<Comment> comms =CommentDAO.getCommentsByOwner(user.getId());
				for(Comment c: comms) {
					c.setDeleted(true);
					CommentDAO.updateComment(c);
				}
				
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
		}
	}

}
