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
			return "redirect:/member/login/form";		// login form ��û���� redirect
		}

		MovieDao mv = new MovieDao();

		//��ȭ ���̵�
		String movie = request.getParameter("movie_id");
	

		System.out.println("�������� " + movie);

		int movie_id = Integer.parseInt(movie);
		Movie m = mv.findMovie(movie_id);
		
		String title = m.getTitle();

		//dao �̿� time list ���ؿ�
		List<Time> timeList = mv.selectTimeList(movie_id);

		//��ȭ�̸�
		request.setAttribute("movie_name", title);
		
		//timeList�� ��� attribute �� ����
		//�� �̸�, �ð� ����, time_id ��� list
		request.setAttribute("timeList", timeList);
		
		request.setAttribute("length", timeList.size());


		return "/movie/choiceTime.jsp";

	}
}
