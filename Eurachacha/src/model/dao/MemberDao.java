package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import model.Ticket;

public class MemberDao {

	public MemberDao() {   // 생성자 
		// JDBC 드라이버 로딩 및 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");   
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}   
	}

	private Connection getConnection() {
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

	// Member 등록
	public int create(Member member) {
		Connection conn = null;
		PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성

		String query = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, member.getMember_id());
			pStmt.setString(2, member.getMember_pw());
			pStmt.setString(3, member.getName());
			pStmt.setString(4, member.getEmail());
			pStmt.setString(5, member.getTel());
			pStmt.setInt(6, 0);
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

	// Member 수정
	public int update(Member member) {
		Connection conn = null;
		PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성

		String query = "UPDATE MEMBER "
				+ "SET user_pw = ?, name = ?, email = ?, tel=? "
				+ "WHERE member_id = ?";

		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, member.getMember_pw());
			pStmt.setString(2, member.getName());
			pStmt.setString(3, member.getEmail());
			pStmt.setString(4, member.getTel());
			pStmt.setString(5, member.getMember_id());
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

	// Member 삭제
	public int remove(String id) {
		Connection conn = null;
		PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성

		try {
			String query = "DELETE FROM MEMBER "
					+ "WHERE member_id = ?";
			
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, id);

			System.out.println(query);
			int result = pStmt.executeUpdate();

			if (result > 0)
				System.out.println("삭제완료");
			else
				System.out.println("삭제 실패");
			return result;
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
		return 0;
	}

	// 주어진 사용자 ID에 해당하는 사용자가 존재하는 지 검사
	public boolean existingUser(String memberId) {
		Connection conn = null;
		PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
		ResultSet rs = null;

		String query = "SELECT count(*) FROM MEMBER WHERE member_id = ?";

		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, memberId);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (SQLException ex) {
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
		return false;
	}

	public List<Ticket> getAllReserve(String id) {
		Connection conn = null;
		PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
		ResultSet rs = null;
		
		String query = "SELECT ticket_id, title, theater_name, time, row_id, col_id, ticket_cost "
				+ "FROM ticket tk "
				+ "JOIN seat s ON tk.seat_id = s.seat_id "
				+ "JOIN movie_time mt ON s.time_id = mt.time_id "
				+ "JOIN movie m ON mt.movie_id = m.movie_id "
				+ "JOIN theater th ON mt.theater_id = th.theater_id "
				+ "WHERE tk.member_id = ? "
				+ "ORDER BY ticket_id DESC";

		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, id);
			rs = pStmt.executeQuery();

			List<Ticket> list = new ArrayList<Ticket>();

			while (rs.next()) {
				int ticket_id = rs.getInt("ticket_id");
				String title = rs.getString("title");
				String theater_name = rs.getString("theater_name");
				String time = rs.getString("time");
				int row_id = rs.getInt("row_id");
				int col_id = rs.getInt("col_id");
				int ticket_cost = rs.getInt("ticket_cost");

				Ticket t = new Ticket(ticket_id, title, theater_name, time, row_id, col_id, ticket_cost);
				list.add(t);
			}
			return list;
		} catch (SQLException ex) {
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

	public Member findMember(String id) {
		Connection conn = null;
		PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
		ResultSet rs = null;

		String query = "SELECT user_pw, name, email, tel FROM member WHERE member_id = ?";

		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, id);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				String user_pw = rs.getString("user_pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				
				Member mem = new Member(id, user_pw, name, email, tel);
				return mem;
			}
		} catch (SQLException ex) {
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
	   
	// Point 확인
	   public Member findPoint(String id) {
	      Connection conn = null;
	      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
	      ResultSet rs = null;
	      
	      String query = "SELECT point FROM member WHERE member_id = ?";
	      
	      Member mem;

	      try {
	         conn = getConnection();
	         pStmt = conn.prepareStatement(query);
	         pStmt.setString(1, id);
	         rs = pStmt.executeQuery();

			while (rs.next()) {
			    int point = rs.getInt("point");
			    
	             mem = new Member(point);
	             return mem;
	         }

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
	      return null;
	   }
}
