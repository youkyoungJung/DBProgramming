package controller.movie;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.dao.ReserveDao;
import model.UnableSeat;

public class SeatChoiceFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form ��û���� redirect
        }
		//�ð� �� �޾ƿ�
		//�������� ��Ʈ�� �޾ƿ��� ��
		String time = request.getParameter("time");
		
		String error = (String)request.getAttribute("seat_choice_error");
		
		if (error != null) {
			request.setAttribute("seat_choice_error", "�¼��� �������ּ���");
		}
		

		ReserveDao rd = new ReserveDao();
		
		int time_id = Integer.parseInt(time);
		
		//���úҰ� �¼� ��ü ����Ʈ
		List<UnableSeat> unable = rd.selectUnableSeat(time_id);
		
		request.setAttribute("unable", unable);
		request.setAttribute("time", time_id);
		
		return "/movie/choiceSeat.jsp";
	}

}
