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
import DAO.SubscribersDAO;
import DAO.UserDAO;
import DAO.VideoDAO;
import model.Comment;
import model.User;
import model.Video;
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
			User owner = UserDAO.getByUserName(username);
			ArrayList<Video> videos = null;
			String status="visiter";
			String isSubscribed="unsuscribed";
			
			
			if(loggedInUser != null) {
				status="logedUser";
				int	isSub=SubscribersDAO.findSubscribed(owner.getId(),loggedInUser.getId());
				if(isSub > 0) {
					isSubscribed="subscribe";
				}
				if(loggedInUser.getUsername().equals(username) || loggedInUser.getRole().toString().equals("ADMIN")) {
					videos=VideoDAO.getUserVideoId(owner.getId()); 
				}else if(owner.isBlocked() == true)  {
					status="blocked";
					Map<String, Object> data = new HashMap<>();
					data.put("status", status);
					ObjectMapper mapper = new ObjectMapper();
					String jsonData = mapper.writeValueAsString(data);
					response.setContentType("application/json");
					response.getWriter().write(jsonData);
					return;
				}
					
			}else {
				videos=VideoDAO.getUserPublicVideoId(owner.getId());
				if(owner.isBlocked() == true) {
					status="blocked";
					Map<String, Object> data = new HashMap<>();
					data.put("status", status);
					ObjectMapper mapper = new ObjectMapper();
					String jsonData = mapper.writeValueAsString(data);
					response.setContentType("application/json");
					response.getWriter().write(jsonData);
					return;
				}
			}
			
			
			ArrayList<User> subs=SubscribersDAO.subscribedOn(owner.getId());
			int subNumber=SubscribersDAO.getSubscribersNumber(owner.getId());
			
			Map<String, Object> data = new HashMap<>();
			data.put("owner", owner);
			data.put("subNumber", subNumber);
			data.put("videos", videos);
			data.put("subs", subs);
			data.put("status", status);
			data.put("user", loggedInUser);
			data.put("isSubscribed",isSubscribed);
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
		//delete,edit,block,unblock za usere na user page
		String status=request.getParameter("status");
		if(status.equals("edit")) {
			String name=request.getParameter("name");
			String surname=request.getParameter("surname");
			String password=request.getParameter("password");
			String description=request.getParameter("description");
			String role=request.getParameter("role");
			String username=request.getParameter("userName");
			Role r;
			if(role.equals("user")) {
				r=Role.USER;
			}
			else {
				r=Role.ADMIN;
			}
			
			User u =UserDAO.getByUserName(username);
			u.setName(name);
			u.setSurname(surname);
			u.setPassword(password);
			u.setDescription(description);
			u.setRole(r);
			UserDAO.Update(u);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			
		}else if(status.equals("delete")) {
			String username=request.getParameter("userName");
			User u =UserDAO.getByUserName(username);
			u.setDeleted(true);
			UserDAO.Update(u);
			
			ArrayList<Video> videos =VideoDAO.getUserVideoId(u.getId());
			for(Video v: videos) {
				v.setDeleted(true);
				VideoDAO.updateVideo(v);
			}
			
			ArrayList<Comment> comms =CommentDAO.getCommentsByOwner(u.getId());
			for(Comment c: comms) {
				c.setDeleted(true);
				CommentDAO.updateComment(c);
			}
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			
		}
		else if(status.equals("block")) {
			String username=request.getParameter("userName");
			
			User u =UserDAO.getByUserName(username);
			u.setBlocked(true);
			UserDAO.Update(u);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			
		}
		else if(status.equals("unblock")) {
			String username=request.getParameter("userName");
			
			User u =UserDAO.getByUserName(username);
			u.setBlocked(false);
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
