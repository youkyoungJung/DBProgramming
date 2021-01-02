package controller.movie;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.dao.MovieDao;
import model.Movie;

public class PlayingMovieListController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form 요청으로 redirect
        }
		
		MovieDao md = new MovieDao();
		
		//Movie 객체로 된 영화 목록 전체를 가져와
		List<Movie> movieList = md.selectAll();
		
		//movieList에 담아 attribute 전송
		request.setAttribute("movieList", movieList);	
		
		//영화 선택 페이지로 이동
		return "/movie/choiceMovie.jsp";
	}

}
