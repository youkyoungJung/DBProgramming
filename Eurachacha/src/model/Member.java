package model;

public class Member {
	private String member_id;
	private String member_pw;
	private String name;
	private String email;
	private String tel;
	private int point;

	public Member() { }

	public Member(String member_id, String name, String mail, String tel) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.email = mail;
		this.tel = tel;
	}

	public Member(String member_id, String user_pw, String name, String mail, String tel) {
		super();
		this.member_id = member_id;
		this.member_pw = user_pw;
		this.name = name;
		this.email = mail;
		this.tel = tel;
	}

	public Member(String member_id, String user_pw, String name, String mail, String tel, int point) {
		super();
		this.member_id = member_id;
		this.member_pw = user_pw;
		this.name = name;
		this.email = mail;
		this.tel = tel;
		this.point = point;
	}
	
	public Member(int point) {
		super();
		this.point = point;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	/* 비밀번호 검사 */
	public boolean matchPassword(String user_pw) {
		if (user_pw == null) {
			return false;
		}
		return this.member_pw.equals(user_pw);
	}

	public boolean isSameUser(String id) {
		return this.member_id.equals(id);
	}
}