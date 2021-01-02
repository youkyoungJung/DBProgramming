package controller.movie;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Movie;
import model.dao.TimeTableDao;
import model.service.MemberManager;

public class ListTimeTableController implements Controller{
	TimeTableDao dao = new TimeTableDao();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		try {
		MemberManager manager = MemberManager.getInstance();
		List<Movie> movies = manager.findMovieWithTimes();
		request.setAttribute("movies", movies);
		}catch(Exception e) {
			System.out.println("movies가 널이다");
		}
		
		return "/movie/timeTable.jsp";
	}

}
