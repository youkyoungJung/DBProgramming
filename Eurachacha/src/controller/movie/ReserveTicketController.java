package controller.movie;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Seat;
import model.dao.ReserveDao;

public class ReserveTicketController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		ReserveDao rd = new ReserveDao();
		
		//좌석들의 상태를 가져옴
		String[] s = request.getParameterValues("seat");
		
		//시간값 가져옴
		String time = request.getParameter("time");

		ArrayList<String> active_s = new ArrayList<String>();
	

		for (int i = 0; i < s.length; i++) {

			//선택된 좌석들만 따로 저장
			if (s[i].contains("active")) {
				active_s.add(s[i]);

				System.out.println(s[i]);
			}
		}
		
		//선택한 좌석이 없을 경우
		if (active_s.size() == 0) {
			request.setAttribute("seat_choice_error", "좌석을 선택해주세요.");
			
			return "/movie/reserve/choice/seat/form";	
		}
		
		int size = active_s.size();
		
		//선택된 좌석들의 row, col을 따로 저장하는 배열
		int[] row = new int[size];
		int[] col = new int[size];

		//1-1-active-up 과 같은 형식으로 넘어오기 때문에 행값과 열값을 따로 빼서 저장하는 작업 ㅍ필요
		for (int i = 0; i < active_s.size(); i++){
			row[i] = Character.getNumericValue(active_s.get(i).charAt(0));
			col[i] = Character.getNumericValue(active_s.get(i).charAt(2));

			System.out.println(row[i] + ", " + col[i] + "\n");
		}
		
		//time_id값
		int time_id = Integer.parseInt(time);
		ArrayList<Seat> seats = new ArrayList<Seat>();
		
		int state = 0;
		
		//seat 생성
		for (int i = 0; i < active_s.size(); i++) {
			state = rd.createSeat(row[i], col[i], time_id);
			
			if (state != -1) {
				System.out.println("생성 완료!");
				
				//생성된 좌석들의 정보들 담아둠(seats)
				seats.add(rd.seatInfo(row[i], col[i], time_id));
			}
		}

		//로그인 하고 있는 아이디 받아옴
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute(MemberSessionUtils.MEMBER_SESSION_KEY);
		
		//ticket 생성
		for (int i = 0; i < active_s.size(); i++) {
			state = rd.createTicket(seats.get(i).getSeat_id(), member_id);
			
			if (state != -1)
				System.out.println("티켓 정보 생성 완료!");
		}

//		request.setAttribute("reserve_seats", seats);
	return "redirect:/movie/roulette.jsp";
	}
}
