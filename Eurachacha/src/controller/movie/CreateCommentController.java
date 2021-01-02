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
        //이전에서 받아온거
      
        int movie_id = Integer.parseInt(request.getParameter("movie_id"));
       String content = request.getParameter("content");
       
       System.out.println(movie_id);
       
        Comment comm = new Comment();
        System.out.println("확인");
       comm.setContent(content);
       System.out.println(content);
       comm.setMember_id(id);
       System.out.println(id);
       comm.setMovie_id(movie_id);
       System.out.println(movie_id);
       
       //리다이렉트 하기전 무비 아이디 알려주기
       request.setAttribute("movie_id", movie_id);
       
        
       try {
         cDao.create(comm);
         return "redirect:/movie/detail?movie_id=" +movie_id;
         
      } catch (Exception e) {      // 예외 발생 시 입력 form으로 forwarding
         System.out.println("예외 발생");
            request.setAttribute("creationFailed", true);
         request.setAttribute("exception", e);
         request.setAttribute("comm", comm);
         return "/movie/commentFrom.jsp";
      }
    }

}