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

import DAO.LikeDislikeDAO;
import DAO.VideoDAO;
import model.LikeDislike;
import model.User;
import model.Video;


/**
 * Servlet implementation class LikeVideoServlet
 */
public class LikeVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//like videa
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Video video = VideoDAO.getVideoById(id);
		
		
		if(loggedInUser!= null) {
			LikeDislike like=LikeDislikeDAO.getVideosByOwner(video.getId(), loggedInUser.getId());
			if(like == null) {
				
				Date d=new Date();
				int lid=LikeDislikeDAO.getLikeId();
				LikeDislike l=new LikeDislike(lid,true, d, video, null, loggedInUser);
				LikeDislikeDAO.addLikes(l);
				LikeDislikeDAO.addVideoLikeDislike(l.getId(),video.getId());
			}
			else if(like!= null && like.isLike() == false) {
				like.setLike(true);
				LikeDislikeDAO.updateLike(like);
			}
		}
		
		int likeNumber =LikeDislikeDAO.getVideosLikeNumber(video.getId());
		int dislikeNumber =LikeDislikeDAO.getVideosDislikeNumber(video.getId());
		video.setNumberOfDislikes(dislikeNumber);
		video.setNumberOfLikes(likeNumber);
		VideoDAO.updateVideo(video);
		
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
		//dislike videa
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Video video = VideoDAO.getVideoById(id);
		
		
		if(loggedInUser!= null) {
			LikeDislike like=LikeDislikeDAO.getVideosByOwner(video.getId(), loggedInUser.getId());
			if(like == null) {
				
				Date d=new Date();
				int lid=LikeDislikeDAO.getLikeId();
				LikeDislike l=new LikeDislike(lid,false, d, video, null, loggedInUser);
				LikeDislikeDAO.addLikes(l);
				LikeDislikeDAO.addVideoLikeDislike(l.getId(),video.getId());
			}
			else if(like!= null && like.isLike() == true) {
				like.setLike(false);
				LikeDislikeDAO.updateLike(like);
			}
		}
		
		int dislikeNumber =LikeDislikeDAO.getVideosDislikeNumber(video.getId());
		int likeNumber =LikeDislikeDAO.getVideosLikeNumber(video.getId());
		video.setNumberOfLikes(likeNumber);
		video.setNumberOfDislikes(dislikeNumber);;
		VideoDAO.updateVideo(video);
		
		Map<String, Object> data = new HashMap<>();
		data.put("dislikeNumber", dislikeNumber);
		data.put("likeNumber", likeNumber);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}

}
