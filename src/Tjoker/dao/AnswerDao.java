package Tjoker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Tjoker.pojo.Answer;
import Tjoker.pojo.Question;

public class AnswerDao {
	Answer a;
	int index=-1;
	//声明jdbc对象
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Answer> list;
		Question q;
	public List<Answer> findAnswer(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from answer where topic_id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			   list=new ArrayList<Answer>();
			while(rs.next()) {
				 a=new Answer();
				a.setContent(rs.getString("content"));
				a.setId(rs.getInt("id"));
				a.setUser_id(rs.getInt("user_id"));
				a.setUser_name(rs.getString("user_name"));
				a.setExplain_time(rs.getDate("explain_time"));
				a.setQuestion_id(rs.getInt("topic_id"));
				list.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int addAnswer(Answer a) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="insert Answer(content,user_id,user_name,topic_id) value(?,?,?,?) ";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setString(1, a.getContent());
			ps.setInt(2, a.getUser_id());
			ps.setString(3, a.getUser_name());
			ps.setInt(4, a.getQuestion_id());
			
			index=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
	}
	public int deletAnswer(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="delete from answer where id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			index=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
	}
	public List<Answer> myAnswer(int user_id) {
		list=new ArrayList<Answer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select* from answer where user_id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			rs=ps.executeQuery();
			   
			while(rs.next()) {
				 Answer a=new Answer();
				a.setContent(rs.getString("content"));
				a.setId(rs.getInt("id"));
				a.setUser_id(rs.getInt("user_id"));
				a.setUser_name(rs.getString("user_name"));
				a.setExplain_time(rs.getDate("explain_time"));
				a.setQuestion_id(rs.getInt("topic_id"));
				list.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
