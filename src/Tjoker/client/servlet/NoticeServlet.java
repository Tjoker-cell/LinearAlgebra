package Tjoker.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tjoker.pojo.Log;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import Tjoker.service.NoticeService;

/**
 * Servlet implementation class NoticeServlet
 */
public class NoticeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//申明日志对象实体类对象
		   Log l=new Log();
		   	//service层对象
		   LogService ls=new LogService();
		   User u= (User)(req.getSession().getAttribute("user"));
		 	  l.setUser_id(u.getId());
		 	  l.setUser_name(u.getUsername());
		 	  l.setUser_role(u.getRole());
		 	
		 	  
		 	  
		String action =req.getParameter("action");
		NoticeService ns=new NoticeService();
		PrintWriter out =resp.getWriter();
		int index=-1;
		String content;
		int id;
		if("editNotice".equals(action)) {
			  l.setLog_action("修改公告");
			  
			id=Integer.parseInt(req.getParameter("id"));
			content =req.getParameter("content");
		index=ns.edtiNotice(id,content);
			if(index>0) {
				l.setUser_state("成功");
    			l.setLog_content("【"+u.getRole()+u.getUsername()+"】修改id:["+id+"]公告内容为：["+content+"]");
				out.print("true");
				out.flush();
				out.close();
			}
			l.setUser_state("失败");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】修改id:["+id+"]公告失败");
		}else	if("addNotice".equals(action)) {
			  l.setLog_action("添加公告");
			String title=req.getParameter("title");
			 content=req.getParameter("content");
			 index=ns.addNotice(title,content);
			if(index>0) {
				l.setUser_state("成功");
    			l.setLog_content("【"+u.getRole()+u.getUsername()+"】新增内容为:["+content+"]公告成功");
				out.print("true");
				out.flush();
				out.close();
			}
			l.setUser_state("失败");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】新增内容为:["+content+"]公告失败");

		}else if("deletNotice".equals(action)) {
			 l.setLog_action("删除公告");
		id=Integer.parseInt(req.getParameter("id"));
			 index=ns.deletNotice(id);
			if(index>0) {
				l.setUser_state("成功");
    			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为:["+id+"]公告成功");
				out.print("true");
				out.flush();
				out.close();
			}
			l.setUser_state("失败");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为:["+id+"]公告失败");
			
		}
		//将log写进数据库中去
				int index1=ls.addLog(l);
				if(index1>0) {
					System.out.println("jisuan日志写入成功");
				}
		
		
	}
}
