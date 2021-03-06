package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Member;
import model.service.MemberManager;
import model.service.MemberNotFoundException;

public class ProfileMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인 여부 확인
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login";		// login form 요청으로 redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();
		String memberId = request.getParameter("memberId");

    	try {
    		Member member = manager.findMember(memberId);	// 사용자 정보 검색
			request.setAttribute("member", member);		// 사용자 정보 저장	
		} catch (Exception e) {				
	        System.out.println("회원이 아니무니다.");
		}	
					
		return "/member/profile.jsp";				// 사용자 보기 화면으로 이동
    }
}
