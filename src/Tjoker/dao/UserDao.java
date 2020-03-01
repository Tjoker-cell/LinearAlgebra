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
import net.sf.json.JSONObject;

public class UserDao {
	//声明jdbc对象
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	//声明变量
	User u=null;
	int index=-1;
	List<User> list;
	
//根据用户名和密码查询用户信息
	public User findUserDao(String username, String password) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						//获取连接对象
						conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
								+ "?useUnicode=true&characterEncoding=UTF-8","root","");
						//创建Sql命令
						String sql="select *from User where username=? and password=?";
						//创建sql命令对象
						ps=conn.prepareStatement(sql);
						//给占位符赋值
						ps.setString(1, username);
						ps.setString(2, password );
						//执行sql
						rs=ps.executeQuery();
						//遍历结果
							while(rs.next()){
								//给变量赋值
								u=new User();
								u.setActiveCode(rs.getString("activeCode"));
								u.setEmail(rs.getString("email"));
								u.setGender(rs.getString("gender"));
								u.setId(rs.getInt("id"));
								u.setIntroduce(rs.getString("introduce"));
								u.setPassword(rs.getString("password"));
								u.setRegistTime(rs.getDate("registTime"));
								u.setRole(rs.getString("role"));
								u.setState(rs.getInt("state"));
								u.setTelephone(rs.getString("telephone"));
								u.setUsername(rs.getString("username"));
							}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				
					//返回结果
					return u;
				}
//查看邮箱是否存在
	public User checkEmailDao(String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select *from User where email=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, email);
			//执行sql
			rs=ps.executeQuery();
			//遍历结果
				while(rs.next()){
					//给变量赋值
					u=new User();
					u.setActiveCode(rs.getString("activeCode"));
					u.setEmail(rs.getString("email"));
					u.setGender(rs.getString("gender"));
					u.setId(rs.getInt("id"));
					u.setIntroduce(rs.getString("introduce"));
					u.setPassword(rs.getString("password"));
					u.setRegistTime(rs.getDate("registTime"));
					u.setRole(rs.getString("role"));
					u.setState(rs.getInt("state"));
					u.setTelephone(rs.getString("telephone"));
					u.setUsername(rs.getString("username"));
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return u;
	}
//处理修改密码
	public int changePwdDao(String eaddress, String newPwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="update user set password=? where email=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, newPwd);
			ps.setString(2, eaddress);
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}
//注册新用户
	public int regClientDao(String email, String username, String password, String gender, String telephone,
			String introduce) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="insert user(username,password,gender,email,telephone,introduce)  values(?,?,?,?,?,?)";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, gender);
			ps.setString(4, email);
			ps.setString(5, telephone);
			ps.setString(6, introduce);
	
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
}
//查找所有user
	public List<User> allUser() {
		list=new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from User";
			//创建sql命令对象
			Statement stm=conn.createStatement();
			    rs=stm.executeQuery(sql);
			while(rs.next()) {
				u=new User();
				u.setActiveCode(rs.getString("activeCode"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getString("gender"));
				u.setId(rs.getInt("id"));
				u.setIntroduce(rs.getString("introduce"));
				u.setPassword(rs.getString("password"));
				u.setRegistTime(rs.getDate("registTime"));
				u.setRole(rs.getString("role"));
				u.setState(rs.getInt("state"));
				u.setTelephone(rs.getString("telephone"));
				u.setUsername(rs.getString("username"));
				list.add(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
//删除指定用户
	public int deleteUser(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="delete from user where id=?";
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
	public int stopUser(int id,int state) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="update user set state=? where id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			
			ps.setInt(1, state);
			ps.setInt(2, id);
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}
	public List<User> findUser(String username) {
		list=new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="select*from User where username=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, username);
			rs=ps.executeQuery();
			while(rs.next()) {
				u=new User();
				u.setActiveCode(rs.getString("activeCode"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getString("gender"));
				u.setId(rs.getInt("id"));
				u.setIntroduce(rs.getString("introduce"));
				u.setPassword(rs.getString("password"));
				u.setRegistTime(rs.getDate("registTime"));
				u.setRole(rs.getString("role"));
				u.setState(rs.getInt("state"));
				u.setTelephone(rs.getString("telephone"));
				u.setUsername(rs.getString("username"));
				list.add(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int editUser(JSONObject data) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="update user set username=?,password=?,email=?,gender=?,role=?  where id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			
			ps.setString(1, data.getString("username"));
			ps.setString(2, data.getString("password"));
			ps.setString(3, data.getString("email"));
			ps.setString(4, data.getString("sex"));
			ps.setString(5, data.getString("role"));
			ps.setInt(6,data.getInt("id"));
			
			
			
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}
	public int addUser(JSONObject data) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="insert user(username,password,gender,email,telephone,role,state)  values(?,?,?,?,?,?,?)";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, data.getString("username"));
			ps.setString(2, data.getString("password"));
			ps.setString(3, data.getString("sex"));
			ps.setString(4, data.getString("email"));
			ps.setString(5, data.getString("telephone"));
			ps.setString(6, data.getString("role"));
			ps.setInt(7, data.getString("state")=="停用"?0:1);
	
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}
	public int deleteFile(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="delete from file where id=?";
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
	public int editFile(int id, String content) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LinearAlgebra"
					+ "?useUnicode=true&characterEncoding=UTF-8","root","");
			//创建Sql命令
			String sql="update file set filename=? where id=?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			
			ps.setString(1, content);
			ps.setInt(2,id);
			
			
			
			//执行sql
			 index=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//返回结果
		return index;
	}
}