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
	
		 // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
		if (MemberSessionUtils.isLoginUser(deleteId, session)) {
			manager.remove(deleteId);				// ����� ���� ����
			return "redirect:/member/logout";		// logout ó��
		}
		
		/* ������ �Ұ����� ��� */
		Member member = manager.findMember(deleteId);	// ����� ���� �˻�
		request.setAttribute("member", member);						
		request.setAttribute("deleteFailed", true);
		String msg = (MemberSessionUtils.isLoginUser("admin", session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/movie/movie.jsp"; 	// ���� ȭ������ �̵� (forwarding)	
	}
}
