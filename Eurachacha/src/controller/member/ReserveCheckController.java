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
		
		// 로그인 여부 확인
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login";		// login form 요청으로 redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute(MemberSessionUtils.MEMBER_SESSION_KEY);
		

		try {
			List<Ticket> ticketList = manager.getAllReserve(memberId);	// 정보 검색
			request.setAttribute("ticketList", ticketList);		// 사용자 정보 저장		
		} catch(Exception e) {
			System.out.println("예매한 거 없음");
		}
		return "/member/ticketingCheck.jsp";				// 사용자 보기 화면으로 이동
    }

}
