package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.MemberManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		try {
			// �𵨿� �α��� ó���� ����
			MemberManager manager = MemberManager.getInstance();
			manager.login(memberId, memberPwd);
	
			// ���ǿ� ����� ���̵� ����
			HttpSession session = request.getSession();
            session.setAttribute(MemberSessionUtils.MEMBER_SESSION_KEY, memberId);
            session.setAttribute("isLogin", MemberSessionUtils.isLoginUser(memberId, session));
            
            //�ڸ�Ʈ���ų� �����Ҷ� �ʿ���
            return "redirect:/movie/list";			
		} catch (Exception e) {
			/* UserNotFoundException�̳� PasswordMismatchException �߻� ��
			 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/member/loginForm.jsp";			
		}	
    }

}
