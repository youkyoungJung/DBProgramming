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
            return "redirect:/member/login/form";		// login form ��û���� redirect
        }
		
		MovieDao md = new MovieDao();
		
		//Movie ��ü�� �� ��ȭ ��� ��ü�� ������
		List<Movie> movieList = md.selectAll();
		
		//movieList�� ��� attribute ����
		request.setAttribute("movieList", movieList);	
		
		//��ȭ ���� �������� �̵�
		return "/movie/choiceMovie.jsp";
	}

}
