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

		// �α��� ���� Ȯ��
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login";		// login form ��û���� redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();
		String memberId = request.getParameter("memberId");

    	try {
			Member member = manager.findPoint(memberId);	
			request.setAttribute("member", member);		
		} catch (Exception e) {				
	        System.out.println("����");
		}	
					
		return "/member/pointCheck.jsp";				// ����� ���� ȭ������ �̵�
    }

}
