package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Member;
import model.Ticket;
import model.service.MemberManager;
import model.service.MemberNotFoundException;

public class ReserveCheckController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// �α��� ���� Ȯ��
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login";		// login form ��û���� redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute(MemberSessionUtils.MEMBER_SESSION_KEY);
		

		try {
			List<Ticket> ticketList = manager.getAllReserve(memberId);	// ���� �˻�
			request.setAttribute("ticketList", ticketList);		// ����� ���� ����		
		} catch(Exception e) {
			System.out.println("������ �� ����");
		}
		return "/member/ticketingCheck.jsp";				// ����� ���� ȭ������ �̵�
    }

}
