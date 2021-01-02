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

      //System.out.print("commId 변환함");
      if (request.getMethod().equals("GET")) {   
          // GET request: 코멘트 수정 form 요청   
         int commId = Integer.parseInt(request.getParameter("comment_id"));
         Comment comm = dao.findComment(commId);   // 수정하려는 커멘트 정보 검색
         String userId = comm.getMember_id();
         int movie_id = comm.getMovie_id();
         
         if (MemberSessionUtils.isLoginUser(userId, session)) {
            request.setAttribute("community", comm);         
               
            return "/movie/commentUpdateForm.jsp";   // 검색한 정보를 update form으로 전송    
         }
         /*삭제가 불가능한경우*/
         request.setAttribute("comment", comm);                  
         request.setAttribute("deleteFailed", true);
         String msg = "타인의 정보는 삭제할 수 없습니다.";                                       
         request.setAttribute("exception", new IllegalStateException(msg));
           return "redirect:/movie/detail?movie_id=" +movie_id;   
       }   
       //post 처리
      String content = request.getParameter("review");
      int commId =  Integer.parseInt(request.getParameter("comm_id"));
      Comment comm = dao.findComment(commId);
      int movie_id = comm.getMovie_id();
      dao.update(commId,content);         
      
      return "redirect:/movie/detail?movie_id=" + movie_id;      
      
    }
}