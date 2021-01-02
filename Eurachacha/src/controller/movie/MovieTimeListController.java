package controller.movie;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.dao.MovieDao;
import model.Movie;
import model.Time;

public class MovieTimeListController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		if (!MemberSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/member/login/form";		// login form 요청으로 redirect
		}

		MovieDao mv = new MovieDao();

		//영화 아이디
		String movie = request.getParameter("movie_id");
	

		System.out.println("설마마맘 " + movie);

		int movie_id = Integer.parseInt(movie);
		Movie m = mv.findMovie(movie_id);
		
		String title = m.getTitle();

		//dao 이용 time list 구해옴
		List<Time> timeList = mv.selectTimeList(movie_id);

		//영화이름
		request.setAttribute("movie_name", title);
		
		//timeList에 담아 attribute 값 전송
		//관 이름, 시간 정보, time_id 담는 list
		request.setAttribute("timeList", timeList);
		
		request.setAttribute("length", timeList.size());


		return "/movie/choiceTime.jsp";

	}
}
