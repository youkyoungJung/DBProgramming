package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Member;
import model.service.MemberManager;
import model.service.MemberNotFoundException;

public class PointCheckController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 확인
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login";		// login form 요청으로 redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();
		String memberId = request.getParameter("memberId");

    	try {
			Member member = manager.findPoint(memberId);	
			request.setAttribute("member", member);		
		} catch (Exception e) {				
	        System.out.println("오류");
		}	
					
		return "/member/pointCheck.jsp";				// 사용자 보기 화면으로 이동
    }

}
