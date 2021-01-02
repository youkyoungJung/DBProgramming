package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.member.DeleteMemberController;
import controller.member.JoinMemberController;
import controller.member.LoginController;
import controller.member.LogoutController;
import controller.member.PlusPointController;
import controller.member.PointCheckController;
import controller.member.ProfileMemberController;
import controller.member.ReserveCheckController;
import controller.member.UpdateMemberController;
import controller.movie.CancelTicketController;
import controller.movie.CreateCommentController;
import controller.movie.DetailController;
import controller.movie.ListCommentController;
import controller.movie.ListTimeTableController;
import controller.movie.MoveToCommentForm;
import controller.movie.MovieTimeListController;
import controller.movie.MovieViewController;
import controller.movie.PlayingMovieListController;
import controller.movie.RemoveCommentController;
import controller.movie.ReserveTicketController;
import controller.movie.SeatChoiceFormController;
import controller.movie.UpdateCommentController;
import controller.movie.movieSearchController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	mappings.put("/", new ForwardController("index.jsp"));
    	
        mappings.put("/movie/list", new MovieViewController());
        mappings.put("/movie/detail", new DetailController());
   
        //member 관련
        mappings.put("/member/register/form", new ForwardController("/member/registerForm.jsp"));
        mappings.put("/member/login/form", new ForwardController("/member/loginForm.jsp"));
        mappings.put("/member/login", new LoginController());
        mappings.put("/member/logout", new LogoutController());
        mappings.put("/member/join/form", new ForwardController("/member/joinForm.jsp"));
        mappings.put("/member/join", new JoinMemberController());        
        mappings.put("/member/profile/form", new ForwardController("/member/profile.jsp"));
        mappings.put("/member/profile", new ProfileMemberController());
        mappings.put("/member/update/form", new ForwardController("/member/profileUpdate.jsp"));
        mappings.put("/member/update", new UpdateMemberController());
        mappings.put("/member/delete", new DeleteMemberController());

        mappings.put("/member/reserve/check/form", new ForwardController("/member/ticketingCheck.jsp"));
        mappings.put("/member/reserve/check", new ReserveCheckController());
        mappings.put("/member/point/form", new ForwardController("/member/pointCheck.jsp"));
        mappings.put("/member/point", new PointCheckController());
        
        
        //영화 시간표
        mappings.put("/movie/timetable", new ListTimeTableController());
        
        //영화 예매 관련
        mappings.put("/movie/playingmovie", new PlayingMovieListController());
        mappings.put("/movie/timelist", new MovieTimeListController());
        mappings.put("/movie/reserve/choice/seat/form", new SeatChoiceFormController());
        mappings.put("/movie/reserve/ticket", new ReserveTicketController());
        mappings.put("/movie/reserve/ticket/point", new PlusPointController());
        mappings.put("/movie/reserve/ticket/cancel", new CancelTicketController());
        
        // 코멘트 관련 request URI 추가
        mappings.put("/movie/comment/create/form", new MoveToCommentForm());
        mappings.put("/movie/comment/create", new CreateCommentController());
        mappings.put("/movie/comment/update", new UpdateCommentController());
        mappings.put("/movie/comment/remove", new RemoveCommentController());
        
        //무비 검색
        mappings.put("/movie/search", new movieSearchController());
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
