package model.dao;

import java.sql.*;
import java.util.ArrayList;

import model.Ticket;

public class MovieRegisterDao {

   public MovieRegisterDao() {
      
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");   
      } catch (ClassNotFoundException ex) {
         ex.printStackTrace();
      }   
   }
   
   private Connection getConnection() {
      // DBMS¿ÍÀÇ ¿¬°á È¹µæ ...
      String url = // "jdbc:oracle:thin:@localhost:1521:xe";
            "jdbc:oracle:thin:@202.20.119.117:1521:orcl";   
      String user = "dbprog0202";
      String passwd = "dbprog0202";

      // DBMS¿ÍÀÇ ¿¬°á È¹µæ
      Connection conn = null;
      try {
         conn = DriverManager.getConnection(url, user, passwd);
      } catch (SQLException e) {
         e.printStackTrace();
      }    
      return conn;
   }
   
   //Æ¼ÄÏ ¿¹¸Å
   public int registerTicket(String member_id, int row, int col, int time_id, int movie_id){
      Connection conn = null;
      PreparedStatement pStmt = null;
      ResultSet rs = null;
      
      ArrayList<Ticket> list = new ArrayList<Ticket>();
      
      String query = "INSERT INTO ticket "
            + "(ticket_id, member_id, row_id, col_id, ticket_cost, time_id, movie_id) "
            + "VALUES (ticket_num.nextval, ?, ?, ?, 5000, ?, ?)";
      
      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(query);
         
         pStmt.setString(1, member_id);
         pStmt.setInt(2, row);
         pStmt.setInt(3, col);
         pStmt.setInt(4, time_id);
         pStmt.setInt(5, movie_id);
         
         
         return pStmt.executeUpdate();
         
         
      }catch (SQLException ex) {
         ex.printStackTrace();

      } finally {      // ÀÚ¿ø ¹Ý³³
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
      
      return -1;

   }
   
   //Æ¼ÄÏ Ãë¼Ò _ 
   public int cancelTicket(String member_id, int time_id){
      Connection conn = null;
      PreparedStatement pStmt = null;
      ResultSet rs = null;
      
      String query = "DELETE FROM ticket "
            + "WHERE member_id = ? "
            + "AND time_id = ?";
      
      try {
         conn = getConnection();
         pStmt = conn.prepareStatement(query);
         
         pStmt.setString(1, member_id);
         pStmt.setInt(2, time_id);
      
         return pStmt.executeUpdate();
      
      }catch (SQLException ex) {
         ex.printStackTrace();

      } finally {      // ÀÚ¿ø ¹Ý³³
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
      
      return -1;
   }
}