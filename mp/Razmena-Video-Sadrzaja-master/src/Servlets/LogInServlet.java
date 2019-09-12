package Servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.CommentDAO;
import DAO.LikeDislikeDAO;

import model.Comment;
import model.LikeDislike;
import model.User;


/**
 * Servlet implementation class LikeCommentServlet
 */
public class LikeCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//like comment
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Comment comment = CommentDAO.getCommentById(id);
		
		if(loggedInUser!= null) {
			LikeDislike like=LikeDislikeDAO.getCommentByOwner(comment.getId(), loggedInUser.getId());
			if(like == null) {
				Date date=new Date();
				int lId =LikeDislikeDAO.getLikeId();
				LikeDislike l=new LikeDislike(lId, true,date, null, comment, loggedInUser);
				LikeDislikeDAO.addLikes(l);
				LikeDislikeDAO.addCommentLikeDislike(l.getId(),comment.getId());
			}
			else if(like!= null && like.isLike() == false) {
				like.setLike(true);
				LikeDislikeDAO.updateLike(like);
			}
		}
		
		int likeNumber =LikeDislikeDAO.getCommentLikeNumber(comment.getId());
		int dislikeNumber =LikeDislikeDAO.getCommentDislikeNumber(comment.getId());
		
		comment.setLikeNumber(likeNumber);
		comment.setDislikeNumber(dislikeNumber);
		
		CommentDAO.updateComment(comment);
		
		Map<String, Object> data = new HashMap<>();
		data.put("likeNumber", likeNumber);
		data.put("dislikeNumber", dislikeNumber);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Comment comment = CommentDAO.getCommentById(id);
		
		if(loggedInUser!= null) {
			LikeDislike like=LikeDislikeDAO.getCommentByOwner(comment.getId(), loggedInUser.getId());
			if(like == null) {
				Date date=new Date();
				int lId =LikeDislikeDAO.getLikeId();
				LikeDislike l=new LikeDislike(lId, false, date, null, comment, loggedInUser);
				LikeDislikeDAO.addLikes(l);
				LikeDislikeDAO.addCommentLikeDislike(l.getId(),comment.getId());
			}
			else if(like!= null && like.isLike() == true) {
				like.setLike(false);
				LikeDislikeDAO.updateLike(like);
			}
		}
		
		int likeNumber =LikeDislikeDAO.getCommentLikeNumber(comment.getId());
		int dislikeNumber =LikeDislikeDAO.getCommentDislikeNumber(comment.getId());
		comment.setLikeNumber(likeNumber);
		comment.setDislikeNumber(dislikeNumber);
		CommentDAO.updateComment(comment);
		
		Map<String, Object> data = new HashMap<>();
		data.put("likeNumber", likeNumber);
		data.put("dislikeNumber", dislikeNumber);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

}
