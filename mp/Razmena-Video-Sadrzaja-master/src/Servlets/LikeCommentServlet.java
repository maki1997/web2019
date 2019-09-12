package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.CommentDAO;
import DAO.UserDAO;
import DAO.VideoDAO;
import model.Comment;
import model.User;
import model.Video;




/**
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//crud,order za komentare
		String status = request.getParameter("status");
		switch(status) {
		case "delete":
			String s="success";
			try {
				
				int id =Integer.parseInt(request.getParameter("id"));
				Comment comment=CommentDAO.getCommentById(id);
				comment.setDeleted(true);
				CommentDAO.updateComment(comment);
				
			} catch (Exception e) {
				s="faliuer";
				}
			Map<String, Object> data = new HashMap<>();
			data.put("stat", s);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			break;
		case "edit":
			String ss="success";
			try {
				
				int id =Integer.parseInt(request.getParameter("id"));
				String content=request.getParameter("content");
				System.out.println(content);
				Comment comment=CommentDAO.getCommentById(id);
				System.out.println(comment);
				if(content!= null)
				{
					comment.setContent(content);
					Date date=new Date();
					comment.setDate(date);
					CommentDAO.updateComment(comment);
				}
					
				
			} catch (Exception e) {s="faliuer";}
			Map<String, Object> data2 = new HashMap<>();
			data2.put("stat", ss);
			ObjectMapper mapper2 = new ObjectMapper();
			String jsonData2 = mapper2.writeValueAsString(data2);
			response.setContentType("application/json");
			response.getWriter().write(jsonData2);
			break;
		case "add":
			String owner = request.getParameter("owner");
			String content2 = request.getParameter("content");
			int videoId = Integer.parseInt(request.getParameter("video"));
			User u = UserDAO.getByUserName(owner);
			Video v = VideoDAO.getVideoById(videoId);
			Date d = new Date();
			int id = CommentDAO.getCommentId();
			Comment c = new Comment(id,content2, u, v, d, false,0,0);
			CommentDAO.addComment(c);
			
			Map<String, Object> data3 = new HashMap<>();
			data3.put("status", "success");
			data3.put("id", c.getId());
			data3.put("owner", owner);
			data3.put("date", d);
			data3.put("content", content2);
			data3.put("likeNumber", 0);
			data3.put("dislikeNumber", 0);
			ObjectMapper mapper3 = new ObjectMapper();
			String jsonData3 = mapper3.writeValueAsString(data3);
			response.setContentType("application/json");
			response.getWriter().write(jsonData3);
			
			break;
		case "order":
			String stat = "success";
			ArrayList<Comment> comments=null;
			try {
				String column=request.getParameter("column");
				String ascDesc=request.getParameter("ascDesc");
				int videoIdd =Integer.parseInt(request.getParameter("videoId"));
				comments=CommentDAO.orderComments(videoIdd, column, ascDesc);
				
				
			} catch (Exception e) {status="faliuer";}
			Map<String, Object> datao = new HashMap<>();
			datao.put("stat", stat);
			datao.put("comments", comments);
			ObjectMapper mappero = new ObjectMapper();
			String jsonDatao = mappero.writeValueAsString(datao);
			response.setContentType("application/json");
			response.getWriter().write(jsonDatao);
			break;
			
		}
	}

}
