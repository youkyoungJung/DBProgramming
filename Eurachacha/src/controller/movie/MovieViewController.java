package controller.movie;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.dao.MovieDao;
import model.Movie;


public class MovieViewController implements Controller {
   private MovieDao movieDao = new MovieDao();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
      List movieList = movieDao.selectAll();
      request.setAttribute("movieList", movieList);

      // ù ȭ������ �̵�(forwarding)
      return "/movie/movie.jsp";        
    }

}
