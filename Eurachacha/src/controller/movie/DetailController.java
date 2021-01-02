package controller.movie;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.dao.CommentDao;
import model.dao.MovieDao;
import model.Comment;
import model.Movie;

public class DetailController implements Controller{

   private MovieDao movieDao = new MovieDao();
   private CommentDao cDao = new CommentDao();
   @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      try {
         int movieId = Integer.valueOf(request.getParameter("movie_id").trim());
         Movie movie = movieDao.findMovie(movieId);
         List<Movie> movieList = movieDao.selectAll();
          request.setAttribute("movieList", movieList);
          
          List<Comment> commList = cDao.findCommentList(movieId);
         
          for(Comment comm : commList) {
             System.out.println(comm.getId());
          }
          request.setAttribute("movie", movie);
          request.setAttribute("commList", commList);   // 찾은 코멘트 저장   
         
      }catch(NumberFormatException e) {
         
      }
         
     
       
 
      return "/movie/detail.jsp";
   }
}