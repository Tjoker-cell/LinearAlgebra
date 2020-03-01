package Tjoker.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import Tjoker.pojo.Answer;
import Tjoker.pojo.Log;
import Tjoker.pojo.Question;
import Tjoker.pojo.User;
import Tjoker.service.AnswerService;
import Tjoker.service.LogService;
import Tjoker.service.QuestionService;

/**
 * Servlet implementation class AnswerServlet
 */
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  //获取业务层
		//申明日志对象实体类对象
	   Log l=new Log();
	   	//service层对象
	   LogService ls=new LogService();
	   AnswerService as=new AnswerService();
	   QuestionService qs=new QuestionService();
	   String action=req.getParameter("action");
	   System.out.println(action);
	   PrintWriter out =resp.getWriter();
	 User u= (User)(req.getSession().getAttribute("user"));
	 l.setUser_id(u.getId());
	 l.setUser_name(u.getUsername());
	 l.setUser_role(u.getRole());
	
	   if("myAnswer".equals(action)) {
		   int user_id=Integer.parseInt(req.getParameter("user_id"));
		   List<Answer> list=as.myAnswer(user_id);
		   System.out.println("mysanswer=="+list);
		   req.getSession().setAttribute("myanswer", list);
		   l.setLog_action("访问我的回答");
		   //重定向到
		   if(list.size()==0) {
			   l.setUser_state("失败");
			   l.setLog_content("【"+u.getRole()+u.getUsername()+"】访问我的解答失败可能原因为：该用户还没有解答任何问题");
			   resp.sendRedirect("client/wenda.jsp");
			   JOptionPane.showMessageDialog(null, "该用户还没有解答任何问题");
			   return;
		   }
		   l.setUser_state("成功");
		   l.setLog_content("【"+u.getRole()+u.getUsername()+"】访问我的解答成功");
		   resp.sendRedirect("client/myAnswer.jsp");
		   
	   }else
	   if("deletAnswer".equals(action)) {
		   l.setLog_action("删除我的回答");
		   int id=Integer.parseInt(req.getParameter("id"));
		   int index=as.deletAnswer(id);
		   System.out.println("delete id:"+id);
		   if(id>0) {
			   l.setUser_state("成功");
			   l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为："+id+"的回答成功");
			   out.print("true");
			   out.flush();
			   out.close();
			  
		   }
			//重定向
		   l.setUser_state("失败");
		   l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为："+id+"的回答失败");
		   
	   }else
	   if("answer".equals(action)) {
		   l.setLog_action("新增我的回答");
			String content=req.getParameter("content");
			String que_id=req.getParameter("que_id");
			String user_id=req.getParameter("user_id");
			String user_name=req.getParameter("user_name");

			System.out.println(que_id);
			System.out.println(content);
			System.out.println(user_id);
			System.out.println(user_name);
			Answer a=new Answer();
			a.setContent(content);
			a.setQuestion_id(Integer.parseInt(que_id));
			a.setUser_id(Integer.parseInt(user_id));
			a.setUser_name(user_name);
			int index=as.addAnswer(a);
			List<Answer> alist=as.findAnswer(Integer.parseInt(que_id));//通过后台传过来的question_id加载相关answer
			System.out.println(alist+"=");
			System.out.println(new Gson().toJson(alist)+"=======");
			req.getSession().removeAttribute("answer");
			req.getSession().setAttribute("answer",alist);
			//重定向
			  l.setUser_state("成功");
			   l.setLog_content("【"+u.getRole()+u.getUsername()+"】新增回答成功");
			resp.sendRedirect("client/wenda_xiangxi.jsp");
	   }else {
		   l.setLog_action("搜索我的回答");

		   int id=Integer.parseInt(req.getParameter("id"));
		   Question q=qs.findQuestion(id);//通过后台传过来的question_id加载question
		   System.out.println(q);
		   	List<Answer> alist=as.findAnswer(id);//通过后台传过来的question_id加载相关answer
		   	req.getSession().setAttribute("question", q);//将对象村崔在作用域中
			req.getSession().setAttribute("answer",alist);
			  l.setUser_state("成功");
			   l.setLog_content("【"+u.getRole()+u.getUsername()+"】搜索id:"+id+"回答成功");
			//重定向
			resp.sendRedirect("client/wenda_xiangxi.jsp");
	   }
	 //将log写进数据库中去
		int index=ls.addLog(l);
		if(index>0) {
			System.out.println("answer日志写入成功");
		}
		
}
}
