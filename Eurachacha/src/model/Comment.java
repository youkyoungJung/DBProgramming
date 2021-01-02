package model;

import java.util.Date;

public class Comment {
   private int id;
   private String name;
   private Date reg_date;
   private String content;
   private int score;
   private int movie_id;
   private String member_id;
   private String movie_title;
   
   public Comment() {}
   
   public Comment(int id, String content) {
      super();      
      this.content = content;
      this.id = id;
   }
   
   public Comment(String name, String content, int score, int movie_id, String member_id) {
      super();
      this.name = name;
      this.content = content;
      this.score = score;
      this.movie_id = movie_id;
      this.member_id = member_id;
   }

   public Comment(int id, String name, Date reg_date, String content, int score, int movie_id, String member_id) {
      super();
      this.id = id;
      this.name = name;
      this.reg_date = reg_date;
      this.content = content;
      this.score = score;
      this.movie_id = movie_id;
      this.member_id = member_id;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   
   public Date getReg_date() {
      return reg_date;
   }

   public void setReg_date(Date reg_date) {
      this.reg_date = reg_date;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public int getMovie_id() {
      return movie_id;
   }

   public void setMovie_id(int movie_id) {
      this.movie_id = movie_id;
   }

   public String getMember_id() {
      return member_id;
   }

   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }

   public String getMovie_title() {
      return movie_title;
   }

   public void setMovie_title(String movie_title) {
      this.movie_title = movie_title;
   }
   
   
   
}