package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.dao.ReserveDao;

public class CancelTicketController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//로그인 하고 있는 아이디 받아옴
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute(MemberSessionUtils.MEMBER_SESSION_KEY);
		
		ReserveDao rd = new ReserveDao();
		
		String t = request.getParameter("ticket_id");
		int ticket_id = Integer.parseInt(t);
		
		System.out.println("따라라란" + request.getParameter("ticket_id"));
		
		int a = rd.cancelPoint(ticket_id, member_id);
		int b = rd.cancelTicket(ticket_id);
		
		if (a != -1 && b != -1)
			System.out.println("정상적으로 티켓 취소 완료");
		
		return "redirect:/member/reserve/check";
	}

}
