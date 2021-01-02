package model;

public class Ticket {
	private String member_id;
	private int ticket_id;
	private int row;
	private int col;
	private int cost;
	private String title;
	private String theater_name;
	private String time;

	public Ticket() {

	}

	public Ticket(int ticket_id, String title, String theater_name, String time, int row, int col, int cost) {
		super();
		this.ticket_id = ticket_id;
		this.row = row;
		this.col = col;
		this.cost = cost;
		this.title = title;
		this.theater_name = theater_name;
		this.time = time;
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheater_name() {
		return theater_name;
	}

	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
