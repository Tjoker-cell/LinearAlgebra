package Tjoker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.Notice;
import Tjoker.pojo.Question;

public class NoticeDao {
	int index=-1;
	//声明jdbc对象
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Notice> list;
		Notice n;
	public List<Notice> allNotice(String searchString) {
		list=new ArrayList<Notice>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from notice where content like CONCAT('%',?,'%') ";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setString(1, searchString);
			rs=ps.executeQuery();
			   list=new ArrayList<Notice>();
			while(rs.next()) {
				 Notice n=new Notice();
				n.setContent(rs.getString("content"));
				n.setN_id(rs.getInt("n_id"));
				n.setN_time(rs.getString("n_time"));
				n.setTitle(rs.getString("title"));
				list.add(n);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Notice> getCurrentNotice(int cPage, int pageSize, String searchString) {
		list=new ArrayList<Notice>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from notice where content like CONCAT('%',?,'%') limit ?,?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setString(1, searchString);
			ps.setInt(2, cPage*pageSize);
			ps.setInt(3, pageSize);
			   rs=ps.executeQuery();
				list=new ArrayList<Notice>();

			while(rs.next()) {
				 Notice n=new Notice();
				n.setContent(rs.getString("content"));
				n.setN_id(rs.getInt("n_id"));
				n.setN_time(rs.getString("n_time"));
				n.setTitle(rs.getString("title"));
				list.add(n);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int addNotice(String title, String content) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="insert notice(title,content)  values(?,?)";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, title);
			ps.setString(2, content);
		
			
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}
	public int deletNotice(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="delete  from notice where n_id=?";
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
	public int editNotice(int id, String content) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="update notice  set content=? where n_id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, content);
			ps.setInt(2, id);
			
			
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}

}
