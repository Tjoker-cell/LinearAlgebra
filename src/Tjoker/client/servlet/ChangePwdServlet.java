package Tjoker.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Tjoker.pojo.Log;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import Tjoker.service.UserService;

/**
 * Servlet implementation class ChangePwdServlet
 */
public class ChangePwdServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取请求信息
		//申明日志对象实体类对象
		   Log l=new Log();
		   	//service层对象
		   LogService ls=new LogService();
		HttpSession hs=req.getSession();
		String eaddress=(String) hs.getAttribute("emailAddress");
		String newPwd=req.getParameter("newPwd");
		//获取service成
		UserService us=new UserService();
		int index=us.changePwdService(eaddress,newPwd);
		//设置流输出
		PrintWriter out=resp.getWriter();
		 User u= (User)(req.getSession().getAttribute("user"));
		 l.setUser_id(u.getId());
		 l.setUser_name(u.getUsername());
		 l.setUser_role(u.getRole());
		 l.setLog_action("修改密码");
		if(index>0){
			l.setUser_state("成功");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】修改密码为:["+newPwd+"]成功");
			out.write("true");
			//将log写进数据库中去
			int index1=ls.addLog(l);
			if(index1>0) {
				System.out.println("chang日志写入成功");
			}
			return ;
		}else {
			l.setUser_state("失败");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】修改密码失败");
			out.write("false");
			//将log写进数据库中去
			int index1=ls.addLog(l);
			if(index1>0) {
				System.out.println("chang日志写入失败");
			}
			return ;
		}
	}

}
