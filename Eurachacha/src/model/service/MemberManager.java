package model.service;

import model.Member;
import model.Movie;
import model.Ticket;
import model.Time;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.MemberDao;
import model.dao.TimeTableDao;

public class MemberManager {
	private static MemberManager mMember = new MemberManager();
	private MemberDao mDao;
	private TimeTableDao tDao;

	private MemberManager() {
		try {
			mDao = new MemberDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MemberManager getInstance() {
		return mMember;
	}

	public int create(Member member) throws SQLException, ExistingUserException {
		if (mDao.existingUser(member.getMember_id()) == true) {
			throw new ExistingUserException(member.getMember_id() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return mDao.create(member);
	}

	public int update(Member member) throws SQLException {
		return mDao.update(member);
	}

	public void remove(String id) throws SQLException {
		mDao.remove(id);
	}

	public Member findMember(String id) throws SQLException, MemberNotFoundException {
		Member m = mDao.findMember(id);

		if (m == null) {
			throw new MemberNotFoundException(id + "�� �������� �ʴ� ���̵��Դϴ�.");
		}

		return m;
	}

	public Member findPoint(String id) throws SQLException, MemberNotFoundException {
		Member m = mDao.findPoint(id);

		if (m == null) {
			throw new MemberNotFoundException(id + "�� �������� �ʴ� ���̵��Դϴ�.");
		}

		return m;
	}

	public boolean login(String id, String pwd)
			throws SQLException, MemberNotFoundException, PasswordMismatchException {
		Member m = findMember(id);

		if (!m.matchPassword(pwd)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		} 
		return true;
	}

	public List<Ticket> getAllReserve(String id) {		
		return mDao.getAllReserve(id);
	}

	public MemberDao getMemberDao() {
		return this.mDao;
	}

	public List<Movie> findMoviesWithTimes(Movie movie){
		List<Movie> movies = tDao.GetMovieWithTimes(movie);
		for(Movie m : movies) {
			for(Time t : m.getTimeList()) {
				t.setEnable(tDao.GetEable(t.getTime_id()));
			}
		}
		
		return movies;
		
	}
	
	public List<Movie> findMovieWithTimes(){
		List<Movie> movies = tDao.GetMovieList();
		List<Movie> movieWithTimes = new ArrayList<Movie>();
		for(Movie movie : movies) {
			List<Movie> list = findMoviesWithTimes(movie);
			movieWithTimes.addAll(list);
		}
		return movieWithTimes;
	}
	
}
