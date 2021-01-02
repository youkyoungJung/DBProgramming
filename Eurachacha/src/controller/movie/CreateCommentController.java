package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Comment;
import model.Movie;
import model.dao.CommentDao;
import model.dao.MovieDao;

public class CreateCommentController implements Controller{
   private CommentDao cDao = new CommentDao();
   
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
       HttpSession session = request.getSession();
       
        String id = MemberSessionUtils.getLoginMemberId(session);
        //�������� �޾ƿ°�
      
        int movie_id = Integer.parseInt(request.getParameter("movie_id"));
       String content = request.getParameter("content");
       
       System.out.println(movie_id);
       
        Comment comm = new Comment();
        System.out.println("Ȯ��");
       comm.setContent(content);
       System.out.println(content);
       comm.setMember_id(id);
       System.out.println(id);
       comm.setMovie_id(movie_id);
       System.out.println(movie_id);
       
       //�����̷�Ʈ �ϱ��� ���� ���̵� �˷��ֱ�
       request.setAttribute("movie_id", movie_id);
       
        
       try {
         cDao.create(comm);
         return "redirect:/movie/detail?movie_id=" +movie_id;
         
      } catch (Exception e) {      // ���� �߻� �� �Է� form���� forwarding
         System.out.println("���� �߻�");
            request.setAttribute("creationFailed", true);
         request.setAttribute("exception", e);
         request.setAttribute("comm", comm);
         return "/movie/commentFrom.jsp";
      }
    }

}