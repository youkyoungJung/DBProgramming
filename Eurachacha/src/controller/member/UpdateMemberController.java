package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.service.MemberManager;

public class UpdateMemberController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute(MemberSessionUtils.MEMBER_SESSION_KEY);
		
		// 로그인 여부 확인
		if (!MemberSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/member/login";		// login form 요청으로 redirect
		}

		if (request.getMethod().equals("GET")) {
			MemberManager manager = MemberManager.getInstance();
			Member member = manager.findMember(memberId);
			if (MemberSessionUtils.isLoginUser(memberId, session)) {
				request.setAttribute("member", member);	
				
				return "/member/profileUpdate.jsp";  
			}

			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("member", member);           
			return "/member/profile.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
		}	

		// POST request (회원정보가 parameter로 전송됨)
		Member updateMember = new Member(
				request.getParameter("memberId"),
				request.getParameter("user_pw"),
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("tel"));

		MemberManager manager = MemberManager.getInstance();
		manager.update(updateMember);	
		request.getParameter("memberId");	
		return "redirect:/member/profile?memberId=" + memberId;
	}
}
