package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.ReserveDao;

public class PlusPointController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ReserveDao rd = new ReserveDao();
		
		String p = request.getParameter("point");
		int point = Integer.parseInt(p);
		
		//로그인 하고 있는 아이디 받아옴
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute(MemberSessionUtils.MEMBER_SESSION_KEY);
		
		int result1 = rd.plusTicketPoint(member_id, point);
		int result2 = rd.plusMemberPoint(member_id);
		int result3 = rd.updateTicketPointState(member_id);
		
		if (result1 != -1 && result2 != -1)
			System.out.println("포인트 적립 완료!");
		
		if (result3 != -1)
			System.out.println("티켓 상태 업데이트 완료!");
		
		return "redirect:/movie/confirmReserve.jsp";
	}

}
