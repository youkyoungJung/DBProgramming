package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Comment;
import model.dao.CommentDao;

public class UpdateCommentController implements Controller{
   private static final Logger log = LoggerFactory.getLogger(UpdateCommentController.class);
   private CommentDao dao = new CommentDao();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   throws Exception {
       HttpSession session = request.getSession();
       String id = MemberSessionUtils.getLoginMemberId(session);

      //System.out.print("commId ��ȯ��");
      if (request.getMethod().equals("GET")) {   
          // GET request: �ڸ�Ʈ ���� form ��û   
         int commId = Integer.parseInt(request.getParameter("comment_id"));
         Comment comm = dao.findComment(commId);   // �����Ϸ��� Ŀ��Ʈ ���� �˻�
         String userId = comm.getMember_id();
         int movie_id = comm.getMovie_id();
         
         if (MemberSessionUtils.isLoginUser(userId, session)) {
            request.setAttribute("community", comm);         
               
            return "/movie/commentUpdateForm.jsp";   // �˻��� ������ update form���� ����    
         }
         /*������ �Ұ����Ѱ��*/
         request.setAttribute("comment", comm);                  
         request.setAttribute("deleteFailed", true);
         String msg = "Ÿ���� ������ ������ �� �����ϴ�.";                                       
         request.setAttribute("exception", new IllegalStateException(msg));
           return "redirect:/movie/detail?movie_id=" +movie_id;   
       }   
       //post ó��
      String content = request.getParameter("review");
      int commId =  Integer.parseInt(request.getParameter("comm_id"));
      Comment comm = dao.findComment(commId);
      int movie_id = comm.getMovie_id();
      dao.update(commId,content);         
      
      return "redirect:/movie/detail?movie_id=" + movie_id;      
      
    }
}