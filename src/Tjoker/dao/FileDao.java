package Tjoker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.User;

public class FileDao {
	//声明jdbc对象
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			//声明变量
			FileUpDown	f;
			List<FileUpDown> list;
			int index=-1;
	public int insert(FileUpDown f) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			Object[] param = { f.getId(), f.getUuidname(), f.getFilename(),
					f.getSavepath(), f.getDescription(), f.getUsername() };
			String sql="insert into file(Uuidname, Filename,Savepath,Description) values(?,?,?,?)";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, f.getUuidname());
			ps.setString(2, f.getFilename());
			ps.setString(3, f.getSavepath());
			ps.setString(4, f.getDescription());
//			ps.setString(5, f.getUsername());
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
		
	
	
		//返回结果
		

}
	public List<FileUpDown> list() {
		list=new ArrayList<FileUpDown>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from file  ";
			//创建sql命令对象
			Statement stm=conn.createStatement();
			    rs=stm.executeQuery(sql);
			while(rs.next()) {
			FileUpDown	f=new FileUpDown();
				f.setId(rs.getString("id"));
				f.setDescription(rs.getString("description"));
				f.setFilename(rs.getString("filename"));
				f.setSavepath(rs.getString("savepath"));
				f.setUploadtime(rs.getDate("uploadtime"));
				f.setUsername(rs.getString("username"));
				f.setUuidname(rs.getString("uuidname"));
				list.add(f);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	
	
		//返回结果
	}
	public FileUpDown downloadFileDao(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from file where id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				f=new FileUpDown();
				f.setId(rs.getString("id"));
				f.setDescription(rs.getString("description"));
				f.setFilename(rs.getString("filename"));
				f.setSavepath(rs.getString("savepath"));
				f.setUploadtime(rs.getDate("uploadtime"));
				f.setUsername(rs.getString("username"));
				f.setUuidname(rs.getString("uuidname"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
		
	}
	
		//返回结果
}