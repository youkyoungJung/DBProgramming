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
import model.service.MemberManager;

public class RemoveCommentController implements Controller{

   private static final Logger log = LoggerFactory.getLogger(RemoveCommentController.class);
   private CommentDao dao = new CommentDao();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   throws Exception {
 
      HttpSession session = request.getSession();   
      int deleteId = Integer.parseInt(request.getParameter("comment_id"));

      Comment comm = dao.findComment(deleteId);
      String userId = comm.getMember_id();   
      int movie_id = comm.getMovie_id();
      
      if (MemberSessionUtils.isLoginUser(userId, session)) {      
         dao.remove(deleteId);      
         System.out.println("삭제까지 됨");
       }   
       
      /* 삭제가 불가능한 경우 */
      request.setAttribute("comment", comm);                  
      request.setAttribute("deleteFailed", true);
      String msg = "타인의 정보는 삭제할 수 없습니다.";                                       
      request.setAttribute("exception", new IllegalStateException(msg));
        return "redirect:/movie/detail?movie_id=" + movie_id;            
    }
}