package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comment;

public class CommentDao {
   
   public CommentDao() {   // 생성자 
      // JDBC 드라이버 로딩 및 등록
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");   
      } catch (ClassNotFoundException ex) {
         ex.printStackTrace();
      }   
   }
   
   private static Connection getConnection() {
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
   
   public int create(Comment comm){
      Connection conn = null;
      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
      ResultSet rs = null;
      
      String sql = "INSERT INTO MOVIE_COMMENT VALUES ( ?, SYSDATE, ?, ?, comment_seq.nextval) ";      
      
      try {         
         conn = getConnection();
         pStmt = conn.prepareStatement(sql);
         pStmt.setString(1, comm.getContent());
         pStmt.setInt(2, comm.getMovie_id());
         pStmt.setString(3, comm.getMember_id());
         
         return pStmt.executeUpdate();
         
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
      return 0;         
   }
   
   public int update(int id, String content) {
      Connection conn = null;
      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
      ResultSet rs = null;
      
      String sql = "UPDATE MOVIE_COMMENT "
               + "SET reg_date=SYSDATE, content=? "
               + "WHERE comment_id=?";

      try {            
         conn = getConnection();
         
         pStmt = conn.prepareStatement(sql);
         pStmt.setString(1, content);
         pStmt.setInt(2, id);
         
         return pStmt.executeUpdate();
   
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
      return 0;
   }

   public int remove(int commId) {
      Connection conn = null;
      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
      ResultSet rs = null;
      
      String sql = "DELETE FROM MOVIE_COMMENT WHERE comment_id=?";      

      try {         
         conn = getConnection();
         pStmt = conn.prepareStatement(sql);
         pStmt.setInt(1, commId);
         
         return pStmt.executeUpdate();
         
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
      return 0;
   }
   
   public List<Comment> findCommentList(int movieId){
      Connection conn = null;
      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
      ResultSet rs = null;
      
        String sql = "SELECT * "
                 + "FROM MOVIE_COMMENT "
                 + "WHERE movie_id=? "
                 + "ORDER BY comment_id"; 
         
      try {   
         conn = getConnection();
         pStmt = conn.prepareStatement(sql);
         pStmt.setInt(1, movieId);
         rs = pStmt.executeQuery();
         
         List<Comment> commList = new ArrayList<Comment>();   
         while (rs.next()) {
            Comment comm = new Comment();      
            comm.setId(rs.getInt("comment_id"));
            comm.setMovie_id(movieId);
            comm.setReg_date(rs.getDate("reg_date"));
            comm.setContent(rs.getString("content"));
            comm.setMember_id(rs.getString("member_id"));
            
            commList.add(comm);            // List에 Community 객체 저장
         }      
         return commList;               
         
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


   public Comment findComment(int commId){
      Connection conn = null;
      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
      ResultSet rs = null;
      
        String sql = "SELECT * "
                 + "FROM MOVIE_COMMENT "
                 + "WHERE comment_id = ?";      
         
      try {   
         conn = getConnection();
         pStmt = conn.prepareStatement(sql);
         pStmt.setInt(1, commId);
         rs = pStmt.executeQuery();
         
         Comment comm = null;
         while (rs.next()) {
            comm = new Comment();      
            comm.setId(rs.getInt("comment_id"));
            comm.setReg_date(rs.getDate("reg_date"));
            comm.setMovie_id(rs.getInt("movie_id"));
            comm.setContent(rs.getString("content"));
            comm.setMember_id(rs.getString("member_id"));
         }      
         return comm;               
         
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
   public int countComment(int movieId) {
      Connection conn = null;
      PreparedStatement pStmt = null;         // PreparedStatment 참조 변수 생성
      ResultSet rs = null;
      
        String sql = "SELECT count(*) "
                 + "FROM MOVIE_COMMENT "
                 + "WHERE movie_id = ?";      
         
      try {   
         conn = getConnection();
         pStmt = conn.prepareStatement(sql);
         pStmt.setInt(1, movieId);
         rs = pStmt.executeQuery();
         
         int count = rs.getInt("count(*)");
               
         return count;               
         
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
      return 0;
   }
   
}