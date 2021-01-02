package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Movie;
import model.dao.MovieDao;

public class MoveToCommentForm implements Controller{

   private MovieDao movieDao = new MovieDao();
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      //�α��ξ��ϸ� ���� ���� �α����ؾ���.
      if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";      // login form ��û���� redirect
        }
      
      int movieId = Integer.valueOf(request.getParameter("movie_id"));
      Movie movie_id = movieDao.findMovie(movieId);
      
      request.setAttribute("movie_id", movie_id);
      System.out.println(movieId);
      
      return "/movie/commentForm.jsp";
   }

}