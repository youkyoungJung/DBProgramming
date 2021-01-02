package model.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Member;
import model.Point;

public class PointDao {

   public PointDao() {   // 생성자 
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

   // Point 추가
   public int addPoint(Point point, Member member, int add) {
      Connection conn = null;
      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성

      String query = "UPDATE MEMBER "
            + "SET point = ? + ? "
            + "WHERE userid = ?";

      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(query);
         pStmt.setInt(1, point.getPoint());
         pStmt.setInt(2, add);
         pStmt.setString(3, member.getMember_id());
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

   // Point 취소
   public int removePoint(Point point, Member member, int remove) {
      Connection conn = null;
      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성

      String query = "UPDATE MEMBER "
            + "SET point = ? - ? "
            + "WHERE userid = ?";

      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(query);
         pStmt.setInt(1, point.getPoint());
         pStmt.setInt(2, remove);
         pStmt.setString(3, member.getMember_id());
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
