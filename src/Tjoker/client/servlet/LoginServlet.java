package Tjoker.client.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import Tjoker.pojo.Log;
import Tjoker.pojo.Notice;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import Tjoker.service.NoticeService;
import Tjoker.service.UserService;
import javafx.scene.control.Alert;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
   @Override
   /*
    * @author zhangjin
    * 
    * */
  
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//申明日志对象实体类对象
	   Log l=new Log();
	   	//service层对象
	   LogService ls=new LogService();
	   //获取请求信息
	   String username=req.getParameter("username");
	   String password=req.getParameter("password");
	   //处理请求信息
	   		//调用service层方法
	   			UserService us=new UserService();
	   			NoticeService ns=new NoticeService(); 
	   			//响应处理结果
					User u=us.loginService(username ,password );
					//将登录员信息放进session中去
					HttpSession hs=req.getSession();
					//判断密码是否正确
					if(u!=null) {
						//判断状态
						if(u.getState()==1) {
							System.out.println(u);
							// 3.登录成功，将用户存储到session中
							hs.setAttribute("user", u);
							l.setUser_name(username);//存储到日志中去
							l.setLog_action("登录");
							l.setUser_role(u.getRole());
							l.setUser_role(u.getRole());
							l.setUser_id(u.getId());
						
							List<Notice> notice=ns.allNotice("");//获取notice
							System.out.println("notice=="+notice);
							hs.setAttribute("notice", notice);//添加到session中去
							//判断用户类型
							if("超级用户".equals(u.getRole())) {
								l.setUser_state("成功");
								resp.sendRedirect("admin/main/main.jsp");
								l.setLog_content("【超级用户"+username+"】成功登录");
								
							}else {
								l.setUser_state("成功");
								l.setLog_content("【普通用户"+username+"】成功登录");
								resp.sendRedirect("client/main.jsp");
								
							}
							
						}else {
							l.setUser_state("失败");
							l.setLog_content("【"+username+"】登录失败可能原因为：用户被停用或未激活");
							hs.setAttribute("message", "用户被停用或未激活");
							req.getRequestDispatcher("/client/login.jsp").forward(req, resp);
							
						}	
						
					}else {
						l.setUser_state("失败");
						l.setLog_content("【"+username+"】登录失败可能原因为：密码或账号错误");
						hs.setAttribute("message", "密码或账号错误！");
						req.getRequestDispatcher("/client/login.jsp").forward(req, resp);
						
					}
	   			
	   			//将log写进数据库中去
					int index=ls.addLog(l);
	   			if(index>0) {
	   				System.out.println("login日志写入成功");
	   			}
	   			
	   			
	}

}
