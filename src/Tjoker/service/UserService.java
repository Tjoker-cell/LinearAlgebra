package Tjoker.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import Tjoker.dao.UserDao;
import Tjoker.pojo.User;
import net.sf.json.JSONObject;
	
public class UserService {
	//获取dao层
			UserDao ud=new UserDao();
//用户登录
	public User loginService(String username, String password) {
		//根据用户名和密码在数据库中查找用户
		
		User user=ud.findUserDao(username ,password );
		return user;
	}
//效验邮箱
	
	public User checkEmailService(String email) {
		//根剧后台传过来的数据进行效验
		User useremail=ud.checkEmailDao(email);
		return useremail;
	}
//修改密码

	public int changePwdService(String eaddress, String newPwd) {
		int index=ud.changePwdDao(eaddress,newPwd);
		return index;
	}
//注册用户
	public int regClientService(String email, String username, String password, String gender, String telephone,
			String introduce) {
		int index=ud.regClientDao(email,username,password,gender,telephone,introduce);
		return index;
	}

	public List<User> allUser() {
		List<User> uList=ud.allUser();
		return  uList;
	}

	public int deletUser(int id) {
		int index=ud.deleteUser(id);
		return index;
	}

	public int stopUser(int id,int state) {
		int index=ud.stopUser(id,state);
		return index;
	}

	public List<User> findUser(String username) {
		List<User> uList=ud.findUser(username);
		return  uList;
	}

	public int editUser(JSONObject data) {
		int index=ud.editUser(data);
		return index;
	}

	public int addUser(JSONObject data) {
		int index=ud.addUser(data);
		return index;
	}

	public int deletFile(int id) {
		int index=ud.deleteFile(id);
		return index;
	}

	public int editFile(int id, String content) {
		// TODO Auto-generated method stub
		return ud.editFile(id,content);
	}

}
