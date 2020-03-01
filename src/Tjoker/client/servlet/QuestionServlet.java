package Tjoker.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import Tjoker.pojo.Log;
import Tjoker.pojo.Question;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import Tjoker.service.QuestionService;
import net.sf.json.JSONObject;


public class QuestionServlet extends HttpServlet {
//获取业务层
	QuestionService qs=new QuestionService();
	//申明日志对象实体类对象
	   Log l=new Log();
	   	//service层对象
	   LogService ls=new LogService();
	  
	 	  
	//申明对象question
	Question q=new Question();
	User u=new User();
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 User u= (User)(req.getSession().getAttribute("user"));
	 	  l.setUser_id(u.getId());
	 	  l.setUser_name(u.getUsername());
	 	  l.setUser_role(u.getRole());
	 	
	 	  
		PrintWriter out =resp.getWriter();	
		String action=req.getParameter("name");
		System.out.println("action=="+action);
		
		   if("myQuestion".equals(action)) {
			   int user_id=Integer.parseInt(req.getParameter("user_id"));
			   List<Question> list=qs.myQuestion(user_id);
			   System.out.println("myQuestion==="+list);
			   l.setLog_action("访问我的问题");
			   req.getSession().setAttribute("myquestion", list);
			   if(list.size()==0) {
				   l.setUser_state("失败");
		 			l.setLog_content("【"+u.getRole()+u.getUsername()+"访问我的问题失败可能原因为：改用户没有提问");
				   resp.sendRedirect("client/wenda.jsp");
				   JOptionPane.showMessageDialog(null, "该用户还没有提问");
				   return;
			   }
			   //重定向myQuestion界面
			   l.setUser_state("成功");
	 			l.setLog_content("【"+u.getRole()+u.getUsername()+"访问我的问题成功");
			   resp.sendRedirect("client/myQuestion.jsp");
			   
			   
		   }else
		   if("deletQuestion".equals(action)) {
			   l.setLog_action("删除提问");
			   int id=Integer.parseInt(req.getParameter("id"));
			   int index=qs.deletQuestion(id);
			   System.out.println("delete id:"+id);
			   if(id>0) {
				   l.setUser_state("成功");
		 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除问题id:["+id+"]成功");
				   out.print("true");
				   out.flush();
				   out.close();
				  
			   }
			   l.setUser_state("失败");
	 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除问题id:["+id+"]失败");
			   
		   }else
		if("wenda".equals(action)) {
			List<Question> qlist=qs.allQuestion();
			System.out.println(qlist.size());
			System.out.println();
      	   	out.print(new Gson().toJson(qlist) );;
			out.flush();
			out.close();
			
		}else {
		
		String title=req.getParameter("title");
			String content=req.getParameter("context");
			System.out.println(title+";"+content);
			HttpSession hs=req.getSession();
			u=(User) hs.getAttribute("user");
			  l.setLog_action("添加提问");
			//将属性封装到question中去
		q.setContent(content);
		q.setTitle(title);
		q.setUser_id(u.getId());
		q.setUser_name(u.getUsername());
		int index=qs.addQuestion(q);
		System.out.println(index);
		if(index>0) {
			 l.setUser_state("成功");
	 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】添加问题成功");
			out.print("true");
			out.flush();
			out.close();
		}
		 l.setUser_state("成功");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】添加问题成功");
		}
		 //将log写进数据库中去
			int index1=ls.addLog(l);
			if(index1>0) {
				System.out.println("Question日志写入成功");
			}
		}
	
}
