package Tjoker.pojo;

import java.security.Timestamp;
import java.sql.Date;

public class Answer {
	int id;
	String content;
	String user_name;
	int user_id;
	Date explain_time;
	int question_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getExplain_time() {
		return explain_time;
	}
	public void setExplain_time(Date explain_time) {
		this.explain_time = explain_time;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	
}
