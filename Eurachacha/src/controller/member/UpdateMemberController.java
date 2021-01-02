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
		
		// �α��� ���� Ȯ��
		if (!MemberSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/member/login";		// login form ��û���� redirect
		}

		if (request.getMethod().equals("GET")) {
			MemberManager manager = MemberManager.getInstance();
			Member member = manager.findMember(memberId);
			if (MemberSessionUtils.isLoginUser(memberId, session)) {
				request.setAttribute("member", member);	
				
				return "/member/profileUpdate.jsp";  
			}

			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
			request.setAttribute("member", member);           
			return "/member/profile.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
		}	

		// POST request (ȸ�������� parameter�� ���۵�)
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
