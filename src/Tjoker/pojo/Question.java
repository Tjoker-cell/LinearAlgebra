package Tjoker.pojo;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

public class Question {
	 int id;
	 String title;
	 String content;
     String  user_name;
     java.sql.Date  publish_time;
     public java.sql.Date getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(java.sql.Date publish_time) {
		this.publish_time = publish_time;
	}
	int user_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
   
}
