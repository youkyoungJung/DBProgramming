package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import controller.member.MemberSessionUtils;
import model.UnableSeat;
import model.Seat;
import model.Time;

public class ReserveDao {
	public ReserveDao() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");   
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}   
	}

	private Connection getConnection() {
		// DBMS와의 연결 획득 ...
		String url = // "jdbc:oracle:thin:@localhost:1521:xe";
				"jdbc:oracle:thin:@202.20.119.117:1521:orcl";   
		String user = "dbprog0202";
		String passwd = "dbprog0202";

		// DBMS와의 연결 획득
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}    
		return conn;
	}


	//선택불가 좌석 만들기 위한 dao
	public List<UnableSeat> selectUnableSeat(int time_id){
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null; 

		ArrayList<UnableSeat> seats = new ArrayList<UnableSeat>();

		//영화에 맞는 시간들을 구하는 쿼리
		String query = "SELECT row_id, col_id, time_id "
				+ "FROM seat s JOIN movie_time mt USING (time_id) "
				+ "WHERE time_id = ?";

		System.out.println(query);
		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, time_id);

			rs = pStmt.executeQuery();

			while (rs.next()) {
				UnableSeat s = new UnableSeat();

				s.setCol(rs.getInt("col_id"));
				s.setRow(rs.getInt("row_id"));
				s.setTime_id(rs.getInt("time_id"));

				seats.add(s);
			}

			return seats;

		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
		return null;
	}


	//seat 테이블에 데이터를 넣기 위한 dao
	public int createSeat(int row, int col, int time_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		String query = "INSERT INTO seat (time_id, seat_id, row_id, col_id) "
				+ "VALUES (?, seat_seq.nextval, ?, ?)";

		System.out.println(query);

		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, time_id);
			pStmt.setInt(2, row);
			pStmt.setInt(3, col);

			return pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}   
		return -1;
	}

	//time 값으로 time_id 알아내는 컬럼
	public int selectTimeid(String time) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		int time_id = 0;
		String query = "SELECT time_id FROM movie_time WHERE time=?";
		try {

			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setString(1, time);

			rs = pStmt.executeQuery();

			while(rs.next()) {
				time_id = rs.getInt("time_id");
			}
			return time_id;

		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
		return time_id;

	}
	
//	//seat_id 찾아내기 위함
	public Seat seatInfo(int row, int col, int time_id){
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null; 

//		ArrayList<Seat> seats = new ArrayList<Seat>();
		Seat s = new Seat();

		//영화에 맞는 시간들을 구하는 쿼리
		String query = "SELECT row_id, col_id, time_id, seat_id "
				+ "FROM seat "
				+ "WHERE row_id=? "
				+ "AND col_id=? "
				+ "AND time_id=? ";

		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, row);
			pStmt.setInt(2, col);
			pStmt.setInt(3, time_id);

			rs = pStmt.executeQuery();

			while (rs.next()) {


				s.setCol(rs.getInt("col_id"));
				s.setRow(rs.getInt("row_id"));
				s.setTime_id(rs.getInt("time_id"));
				s.setSeat_id(rs.getInt("seat_id"));
			}

			return s;

		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
		return null;
	}
	
	//ticket 생성 dao -> 단, 포인트 룰렛을 돌리지 않았기 때문에 포인트 값은 null이 들어가야 한다.
	public int createTicket(int seat_id, String member_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		
		System.out.println("싯 아이디 " + seat_id);
		System.out.println("멤 아이디 " + member_id);
		
		//save_state 값은 member 테이블의 point에 적립이 됐는지, 안 됐는지를 표시하기 위함
		//초기값 : 0, 적립이 완료된 것은 1
		String query = "INSERT INTO ticket (member_id, ticket_id, ticket_cost, reserve_time, seat_id, save_state) "
				+ "VALUES (?, ticket_num.nextval, 5000, TO_CHAR(SYSDATE, 'HH:MM:SS'), ?, 0)";

		System.out.println(query);

		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setString(1, member_id);
			pStmt.setInt(2, seat_id);

			return pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}   
		return -1;
	}
	
	//티켓당 포인트
	public int plusTicketPoint(String member_id, int point){
		Connection conn = null;
		PreparedStatement pStmt = null;

		System.out.println("멤 아이디 " + member_id);
		
		String query = "UPDATE ticket "
				+ "SET ticket_point=? "
				+ "WHERE ticket_point IS NULL "
				+ "AND member_id=? ";

		System.out.println(query);

		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, point);
			pStmt.setString(2, member_id);

			return pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}   
		return -1;
	}
	
	//티켓에 해당하는 포인트 멤버에 적립
	public int plusMemberPoint(String member_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		System.out.println("멤 아이디 " + member_id);
		
		String query = "UPDATE member "
				+ "SET point = point + ( "
				+ "SELECT DISTINCT ticket_point "
				+ "FROM ticket "
				+ "WHERE save_state = 0 "
				+ "AND member_id=? "
				+ ")"
				+ "*"
				+ "(SELECT count(*) "
				+ "FROM ticket "
				+ "WHERE save_state = 0 "
				+ "AND member_id=? "
				+ ")"
				+ "WHERE member_id=?";

		System.out.println(query);

		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setString(1, member_id);
			pStmt.setString(2, member_id);
			pStmt.setString(3, member_id);

			return pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}   
		return -1;
	}
	
	//멤버 포인트에 적립이 완료된 티켓은 save_state 를 1로 바꿔준다
	public int updateTicketPointState(String member_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		System.out.println("멤 아이디 " + member_id);
		
		String query = "UPDATE ticket "
				+ "SET save_state = 1 "
				+ "WHERE member_id=?";

		System.out.println(query);

		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setString(1, member_id);

			return pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}   
		return -1;
	}
	
	//티켓 취소 시, 해당하는 포인트를 회수
	public int cancelPoint(int ticket_id, String member_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		System.out.println("멤 아이디 " + member_id);
		
		String query = "UPDATE member "
				+ "SET point = point - ( "
				+ "SELECT ticket_point "
				+ "FROM ticket "
				+ "WHERE ticket_id=? "
				+ ") "
				+ "WHERE member_id=?";


		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, ticket_id);
			pStmt.setString(2, member_id);

			return pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}   
		return -1;
	}
	
	//seat, ticket에 들어있는 정보 삭제
	public int cancelTicket(int ticket_id){
		Connection conn = null;
		PreparedStatement pStmt = null;
		
		String query = "DELETE FROM seat "
				+ "WHERE seat_id = (SELECT seat_id "
				+ "FROM ticket t JOIN seat s USING (seat_id) "
				+ "WHERE ticket_id=? "
				+ ")";


		try {
			conn = getConnection(); 
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, ticket_id);

			return pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {      // 자원 반납
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}   
		return -1;
	}
}
