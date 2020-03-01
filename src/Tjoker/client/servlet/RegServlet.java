package Tjoker.client.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import Tjoker.pojo.Log;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import Tjoker.service.UserService;
import javafx.scene.control.Alert;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取请求数据
		//申明日志对象实体类对象
		   Log l=new Log();
		   	//service层对象
		   LogService ls=new LogService();
		String email=req.getParameter("email");
		String username=req.getParameter("username");
		String password=req.getParameter("repassword");
		String gender=req.getParameter("gender");
		String telephone=req.getParameter("telephone");
		String introduce=req.getParameter("introduce");
		l.setLog_action("注册");
		l.setUser_name(username);
		
		int index=-1;
		//处理请求数据
			//调用service层
				UserService us=new UserService();
			index=us.regClientService(email,username,password,gender,telephone,introduce);
			System.out.println(index);
				//响应处理结果
				if(index>=0){
					l.setUser_state("注册成功");
	    			l.setLog_content("【用户名为："+username+"】注册成功");
			JOptionPane.showConfirmDialog(null, "注册成功，正在跳转到登录页面");
			resp.sendRedirect("client/login.jsp");
			
		}else {
			l.setUser_state("注册失败");
			l.setLog_content("【用户名为："+username+"】注册失败");
			JOptionPane.showConfirmDialog(null, "注册失败，请重新注册");
			req.getRequestDispatcher("client/reg.jsp").forward(req, resp);
			
		}
				//将log写进数据库中去
    			int index1=ls.addLog(l);
    			if(index1>0) {
    				System.out.println("DownloadServlet日志写入失败");
    			}
	}
	
	
}
