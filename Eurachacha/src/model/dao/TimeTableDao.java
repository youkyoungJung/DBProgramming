package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Movie;
import model.Time;

public class TimeTableDao {
	private static SqlSessionFactory sf;
	
	public TimeTableDao() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sf = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	 public static List<Movie> GetMovieList(){
		 
		 SqlSession sqlSession = sf.openSession();
			try {
				List<Movie> result = sqlSession.getMapper(TimeTableMapper.class).selectMovieInfo();
				return result;
			} finally {
				sqlSession.close();
			}
	 }
	 
	 public static List<Movie> GetMovieWithTimes(Movie movie){
		 SqlSession sqlSession = sf.openSession();
		 try {
				List<Movie> result = sqlSession.getMapper(TimeTableMapper.class).selectMovieWithTimes(movie);
				return result;
			} finally {
				sqlSession.close();
			}
	 }
	 
	 public static int GetEable(int id) {
		 SqlSession sqlSession = sf.openSession();
		 try {
				int result = sqlSession.getMapper(TimeTableMapper.class).countEnable(id);
				
				return result;
			} finally {
				sqlSession.close();
			}
	 }

}
