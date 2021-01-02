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
		
		//�ش� ��ȭ�� �ڸ�Ʈ �˻�
		List<Comment> commList = dao.findCommentList(movieId);
		
		request.setAttribute("commList", commList);	// ã�� �ڸ�Ʈ ����				
		return "/movie/detail.jsp";				// �ڸ�Ʈ ����Ʈ ȭ������ �̵�
    }
}
