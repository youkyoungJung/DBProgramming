package controller.movie;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.MovieDao;

public class movieSearchController implements Controller {

	   private MovieDao movieDao = new MovieDao();

	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        
	      String movie_title = request.getParameter("search");
	      List movieList = movieDao.movieSearch(movie_title);
	      request.setAttribute("movieList", movieList);

	      // ù ȭ������ �̵�(forwarding)
	      return "/movie/movie.jsp";        
	    }
}
