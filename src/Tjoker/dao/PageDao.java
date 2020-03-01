package Tjoker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.User;

public class PageDao {
	//声明jdbc对象
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			//声明变量
			int index=-1;
			int totalNum=0;
			List<FileUpDown> list;
	public List<FileUpDown> getCurrentFile(int currentPage, int pageSize,String searchString) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			//利用concat对变量进行连接进行模糊查询
			String sql="select*from file where filename like CONCAT('%',?,'%') limit ?,?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setString(1, searchString);
			ps.setInt(2, currentPage*pageSize);
			ps.setInt(3, pageSize);
			rs=ps.executeQuery();
			list=new ArrayList<FileUpDown>();
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
	public List<FileUpDown> list(String searchString) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			//利用concat对变量进行连接进行模糊查询
			String sql="select*from file where filename like CONCAT('%',?,'%') ";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			ps.setString(1, searchString);
		
			rs=ps.executeQuery();
			list=new ArrayList<FileUpDown>();
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
		
	
	}
}
//sql语句利用concat对变量进行连接进行模糊查询