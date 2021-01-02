package controller.movie;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Comment;
import model.dao.CommentDao;

public class ListCommentController implements Controller{
	private CommentDao dao = new CommentDao();
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		
		//해당 영화에 코멘트 검색
		List<Comment> commList = dao.findCommentList(movieId);
		
		request.setAttribute("commList", commList);	// 찾은 코멘트 저장				
		return "/movie/detail.jsp";				// 코멘트 리스트 화면으로 이동
    }
}
