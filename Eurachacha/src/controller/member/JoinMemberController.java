package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Member;
import model.service.ExistingUserException;
import model.service.MemberManager;

public class JoinMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = new Member(
					request.getParameter("memberId"),
					request.getParameter("user_pw"),
					request.getParameter("name"),
					request.getParameter("email"),
					request.getParameter("tel"));	

		try {
			MemberManager manager = MemberManager.getInstance();
			manager.create(member);

			return "redirect:/member/login/form";
		} catch (ExistingUserException e) {	
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("member", member);
			return "/member/joinForm.jsp";
		}
	}

}
