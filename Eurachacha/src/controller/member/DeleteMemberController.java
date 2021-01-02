package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.service.MemberManager;

public class DeleteMemberController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("memberId");
    	log.debug("Delete User : {}", deleteId);

    	MemberManager manager = MemberManager.getInstance();		
		HttpSession session = request.getSession();	
	
		 // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
		if (MemberSessionUtils.isLoginUser(deleteId, session)) {
			manager.remove(deleteId);				// 사용자 정보 삭제
			return "redirect:/member/logout";		// logout 처리
		}
		
		/* 삭제가 불가능한 경우 */
		Member member = manager.findMember(deleteId);	// 사용자 정보 검색
		request.setAttribute("member", member);						
		request.setAttribute("deleteFailed", true);
		String msg = (MemberSessionUtils.isLoginUser("admin", session)) 
				   ? "시스템 관리자 정보는 삭제할 수 없습니다."		
				   : "타인의 정보는 삭제할 수 없습니다.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/movie/movie.jsp"; 	// 메인 화면으로 이동 (forwarding)	
	}
}
