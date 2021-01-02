package model.dao;


import java.util.List;
import java.util.Map;

import model.Movie;
import model.Time;

public interface TimeTableMapper {
	
	public List<Movie> selectMovieWithTimes(Movie movie);
	
	public List<Movie> selectMovieInfo();
	
	public int countEnable(int id);
	
}
