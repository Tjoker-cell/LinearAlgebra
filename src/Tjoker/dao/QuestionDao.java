package Tjoker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.Question;

public class QuestionDao {
	int index=-1;
	//声明jdbc对象
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Question> qlist;
		Question q;
	public int addQuestion(Question q) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="insert question(title,content,user_id,user_name)  values(?,?,?,?)";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, q.getTitle());
			ps.setString(2, q.getContent());
			ps.setInt(3, q.getUser_id());
			ps.setString(4, q.getUser_name());
			
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}
	public List<Question> allQuestion() {
		qlist=new ArrayList<Question>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from question";
			//创建sql命令对象
			Statement stm=conn.createStatement();
			    rs=stm.executeQuery(sql);
			   
			while(rs.next()) {
				 Question q=new Question();
				q.setContent(rs.getString("content"));
				q.setId(rs.getInt("id"));
				q.setTitle(rs.getString("title"));
				q.setUser_id(rs.getInt("user_id"));
				q.setUser_name(rs.getString("user_name"));
				q.setPublish_time(rs.getDate("publish_time"));
				qlist.add(q);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qlist;
		

}
	public Question findQuestion(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from question where id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			   
			while(rs.next()) {
				 q=new Question();
				q.setContent(rs.getString("content"));
				q.setId(rs.getInt("id"));
				q.setTitle(rs.getString("title"));
				q.setUser_id(rs.getInt("user_id"));
				q.setUser_name(rs.getString("user_name"));
				q.setPublish_time(rs.getDate("publish_time"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return q;
	}
	public int deletQuestion(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="delete  from question where id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setInt(1, id);
			
			
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}
	public List<Question> myQuestion(int user_id) {
		qlist=new ArrayList<Question>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select* from question where user_id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			rs=ps.executeQuery();
			   
			while(rs.next()) {
				 Question q=new Question();
				q.setContent(rs.getString("content"));
				q.setId(rs.getInt("id"));
				q.setTitle(rs.getString("title"));
				q.setUser_id(rs.getInt("user_id"));
				q.setUser_name(rs.getString("user_name"));
				q.setPublish_time(rs.getDate("publish_time"));
				qlist.add(q);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qlist;
		
	}
}