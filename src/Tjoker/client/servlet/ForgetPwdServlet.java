package Tjoker.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import Tjoker.pojo.Log;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import Tjoker.service.UserService;

/**
 * Servlet implementation class ForgetPwd
 */
public class ForgetPwdServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取请求信息
		//申明日志对象实体类对象
		   Log l=new Log();
		   	//service层对象
		   LogService ls=new LogService();
		   User u= (User)(req.getSession().getAttribute("user"));
		   l.setUser_id(u.getId());
			 l.setUser_name(u.getUsername());
			 l.setUser_role(u.getRole());
			 l.setLog_action("找回密码");
			 
			 
		String email=req.getParameter("email");
		System.out.println(email);
		//处理请求数据
			//调用service层对象
		UserService us=new UserService();
			//调用效验方法
		User useremail=us.checkEmailService(email);
		//通过输出流进行返回
		System.out.println(useremail);
		PrintWriter out=resp.getWriter();
		if(useremail==null) {
			l.setUser_state("失败");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】找回密码失败");

				out.write("false");
				
		}else {
			//响应处理结果
			l.setUser_state("成功");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】找回密码成功");
			out.write("true");
			
		}
		//将log写进数据库中去
		int index1=ls.addLog(l);
		if(index1>0) {
			System.out.println("DownloadServlet日志写入成功");
		}
		
		
		
		
	}
}
