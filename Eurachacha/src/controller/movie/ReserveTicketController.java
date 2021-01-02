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
		
		//�¼����� ���¸� ������
		String[] s = request.getParameterValues("seat");
		
		//�ð��� ������
		String time = request.getParameter("time");

		ArrayList<String> active_s = new ArrayList<String>();
	

		for (int i = 0; i < s.length; i++) {

			//���õ� �¼��鸸 ���� ����
			if (s[i].contains("active")) {
				active_s.add(s[i]);

				System.out.println(s[i]);
			}
		}
		
		//������ �¼��� ���� ���
		if (active_s.size() == 0) {
			request.setAttribute("seat_choice_error", "�¼��� �������ּ���.");
			
			return "/movie/reserve/choice/seat/form";	
		}
		
		int size = active_s.size();
		
		//���õ� �¼����� row, col�� ���� �����ϴ� �迭
		int[] row = new int[size];
		int[] col = new int[size];

		//1-1-active-up �� ���� �������� �Ѿ���� ������ �ప�� ������ ���� ���� �����ϴ� �۾� ���ʿ�
		for (int i = 0; i < active_s.size(); i++){
			row[i] = Character.getNumericValue(active_s.get(i).charAt(0));
			col[i] = Character.getNumericValue(active_s.get(i).charAt(2));

			System.out.println(row[i] + ", " + col[i] + "\n");
		}
		
		//time_id��
		int time_id = Integer.parseInt(time);
		ArrayList<Seat> seats = new ArrayList<Seat>();
		
		int state = 0;
		
		//seat ����
		for (int i = 0; i < active_s.size(); i++) {
			state = rd.createSeat(row[i], col[i], time_id);
			
			if (state != -1) {
				System.out.println("���� �Ϸ�!");
				
				//������ �¼����� ������ ��Ƶ�(seats)
				seats.add(rd.seatInfo(row[i], col[i], time_id));
			}
		}

		//�α��� �ϰ� �ִ� ���̵� �޾ƿ�
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute(MemberSessionUtils.MEMBER_SESSION_KEY);
		
		//ticket ����
		for (int i = 0; i < active_s.size(); i++) {
			state = rd.createTicket(seats.get(i).getSeat_id(), member_id);
			
			if (state != -1)
				System.out.println("Ƽ�� ���� ���� �Ϸ�!");
		}

//		request.setAttribute("reserve_seats", seats);
	return "redirect:/movie/roulette.jsp";
	}
}
