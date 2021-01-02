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
		// DBMS���� ���� ȹ�� ...
		String url = // "jdbc:oracle:thin:@localhost:1521:xe";
				"jdbc:oracle:thin:@202.20.119.117:1521:orcl";   
		String user = "dbprog0202";
		String passwd = "dbprog0202";

		// DBMS���� ���� ȹ��
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}    
		return conn;
	}


	//���úҰ� �¼� ����� ���� dao
	public List<UnableSeat> selectUnableSeat(int time_id){
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null; 

		ArrayList<UnableSeat> seats = new ArrayList<UnableSeat>();

		//��ȭ�� �´� �ð����� ���ϴ� ����
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
		} finally {      // �ڿ� �ݳ�
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


	//seat ���̺� �����͸� �ֱ� ���� dao
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
		} finally {      // �ڿ� �ݳ�
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

	//time ������ time_id �˾Ƴ��� �÷�
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
		} finally {      // �ڿ� �ݳ�
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
	
//	//seat_id ã�Ƴ��� ����
	public Seat seatInfo(int row, int col, int time_id){
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null; 

//		ArrayList<Seat> seats = new ArrayList<Seat>();
		Seat s = new Seat();

		//��ȭ�� �´� �ð����� ���ϴ� ����
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
		} finally {      // �ڿ� �ݳ�
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
	
	//ticket ���� dao -> ��, ����Ʈ �귿�� ������ �ʾұ� ������ ����Ʈ ���� null�� ���� �Ѵ�.
	public int createTicket(int seat_id, String member_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		
		System.out.println("�� ���̵� " + seat_id);
		System.out.println("�� ���̵� " + member_id);
		
		//save_state ���� member ���̺��� point�� ������ �ƴ���, �� �ƴ����� ǥ���ϱ� ����
		//�ʱⰪ : 0, ������ �Ϸ�� ���� 1
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
		} finally {      // �ڿ� �ݳ�
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
	
	//Ƽ�ϴ� ����Ʈ
	public int plusTicketPoint(String member_id, int point){
		Connection conn = null;
		PreparedStatement pStmt = null;

		System.out.println("�� ���̵� " + member_id);
		
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
		} finally {      // �ڿ� �ݳ�
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
	
	//Ƽ�Ͽ� �ش��ϴ� ����Ʈ ����� ����
	public int plusMemberPoint(String member_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		System.out.println("�� ���̵� " + member_id);
		
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
		} finally {      // �ڿ� �ݳ�
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
	
	//��� ����Ʈ�� ������ �Ϸ�� Ƽ���� save_state �� 1�� �ٲ��ش�
	public int updateTicketPointState(String member_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		System.out.println("�� ���̵� " + member_id);
		
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
		} finally {      // �ڿ� �ݳ�
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
	
	//Ƽ�� ��� ��, �ش��ϴ� ����Ʈ�� ȸ��
	public int cancelPoint(int ticket_id, String member_id){
		Connection conn = null;
		PreparedStatement pStmt = null;

		System.out.println("�� ���̵� " + member_id);
		
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
		} finally {      // �ڿ� �ݳ�
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
	
	//seat, ticket�� ����ִ� ���� ����
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
		} finally {      // �ڿ� �ݳ�
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
