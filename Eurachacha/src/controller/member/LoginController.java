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
			// 모델에 로그인 처리를 위임
			MemberManager manager = MemberManager.getInstance();
			manager.login(memberId, memberPwd);
	
			// 세션에 사용자 아이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(MemberSessionUtils.MEMBER_SESSION_KEY, memberId);
            session.setAttribute("isLogin", MemberSessionUtils.isLoginUser(memberId, session));
            
            //코멘트쓰거나 예매할때 필요함
            return "redirect:/movie/list";			
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/member/loginForm.jsp";			
		}	
    }

}
