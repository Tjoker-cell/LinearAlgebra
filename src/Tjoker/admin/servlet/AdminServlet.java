package Tjoker.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;

import Tjoker.client.servlet.QuestionServlet;
import Tjoker.pojo.Log;
import Tjoker.pojo.Question;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import Tjoker.service.QuestionService;
import Tjoker.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @Override
       /*
        * @author:zhangjin
        * 
        * 
        * */
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
 	 	  
 	 	  
 	 	  
    	   String action=req.getParameter("action");
    	   System.out.println(action);
    	 //获取业务成对象
    	   UserService us=new UserService();
    	   QuestionService qs=new QuestionService();
    	 	PrintWriter out=resp.getWriter();
    	  //处理请求数据
    	 	if("login".equals(action)) {
    	 		l.setLog_action("退出登录");
    	 		 l.setUser_state("成功");
    	   			l.setLog_content("【"+u.getRole()+u.getUsername()+"】退出登录成功");
    	 		req.getSession().invalidate();//清除session
    	 		resp.sendRedirect("client/login.jsp");
    	 		
    	 	}else
    	 	if("exit".equals(action)) {
    	 		l.setLog_action("退出登录");
    	 		 l.setUser_state("成功");
    	   			l.setLog_content("【"+u.getRole()+u.getUsername()+"】退出登录成功");
    	 		req.getSession().invalidate();//清除session
    	 		resp.sendRedirect("client/login.jsp");

    	 		
    	 	}else
    	 	if("deletLog".equals(action)) {
  	 	 	  l.setLog_action("删除日志");

  	 	   int id=Integer.parseInt(req.getParameter("id"));
  		   System.out.println(id);
  		   int index=ls.deletFile(id);
  		   System.out.println(index);
  		   if(index>0) {  l.setUser_state("成功");
   			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为：["+id+"]的日志成功");
  			out.print("true" );;
      	   	out.flush();
      	   	out.close();
      	  }
  		   l.setUser_state("失败");
  			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为：["+id+"]的日志失败");
  	 	}else
    	 	if("findLog".equals(action)) {
  	 	 	  l.setLog_action("查找用户");

  	 		String username=req.getParameter("username");
  	 		List<Log > log=ls.findLog(username);
  	 	System.out.println(username);
        	   System.out.println(log);
        	   //发回处理结果
        	   	//通过流输出
        	   	out.print(new Gson().toJson(log) );;
        	   	out.flush();
        	   	out.close();
    	 	}else
    	 	if("allLog".equals(action)) {
     		   
         	  List<Log> list=ls.allLog();
         	   System.out.println(list);
         	   //发回处理结果
         	   	//通过流输出
         	   	out.print(new Gson().toJson(list) );;
         	   	out.flush();
         	   	out.close();
     	   }else
    	 	if("editFile".equals(action)) {
    	 	 	  l.setLog_action("编辑文件");
    	 		 int id=Integer.parseInt(req.getParameter("id"));
    	 		 String content=req.getParameter("content");
      		   System.out.println(id);
      		   int index=us.editFile(id,content);
      		   System.out.println(index);
      		   if(index>0) {
      			 l.setUser_state("成功");
      			l.setLog_content("【"+u.getRole()+u.getUsername()+"】编辑id为：["+id+"]的文件成功");
      			out.print("true" );;
          	   	out.flush();
          	   	out.close();
          	   }
      		 l.setUser_state("成功");
  			l.setLog_content("【"+u.getRole()+u.getUsername()+"】编辑id为：["+id+"]的文件失败");
    	 		
    	 	}else
    	 	if("adminQuestion".equals(action)) {
    	 	 	  

    	 		List<Question> list=qs.allQuestion();
    	 		req.getSession().removeAttribute("myquestion");
    	 		req.getSession().setAttribute("myquestion", list);
    	 		if(list.size()==0) {
    	 			resp.sendRedirect("client/wenda.jsp");
    	 			JOptionPane.showMessageDialog(null, "暂时还没有问题请提问");
    	 			return;
    	 		}
    	 		//重定向到admin.jsp
    	 		resp.sendRedirect("admin/question.jsp");
    	 		
    	 		
    	 	}else
    	 	if("deletFile".equals(action)) {
    	 	 	  l.setLog_action("删除文件");

    	 	   int id=Integer.parseInt(req.getParameter("id"));
    		   System.out.println(id);
    		   int index=us.deletFile(id);
    		   System.out.println(index);
    		   if(index>0) {  l.setUser_state("成功");
     			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为：["+id+"]的文件成功");
    			out.print("true" );;
        	   	out.flush();
        	   	out.close();
        	  }
    		   l.setUser_state("失败");
    			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为：["+id+"]的文件失败");
    	 	}else
    	 	if("addUser".equals(action)) {
    	 	 	  l.setLog_action("添加用户");

        	 	JSONObject data=JSONObject.fromObject(req.getParameter("data"));
        	 	int index=us.addUser(data);
    	 		System.out.println(data);
    	 		System.out.println(index);
    	 	 	//通过流输出
    	 		if(index>-1) {	
    	 			  l.setUser_state("成功");
            			l.setLog_content("【"+u.getRole()+u.getUsername()+"】添加用户成功");
    	 			out.print("true");
    	 			}
    	 		else {  l.setUser_state("失败");
      			l.setLog_content("【"+u.getRole()+u.getUsername()+"】添加用户失败");
    	 			out.print("false");
    	 		}
          	   	out.flush();
          	   	out.close();
    	 		
    	 	}else if("editUser".equals(action)) {
    	 	 	  l.setLog_action("编辑用户");

    	 	JSONObject data=JSONObject.fromObject(req.getParameter("data"));
    	 		int index=us.editUser(data);
    	 		System.out.println(data);
    	 		System.out.println(index);
    	 	 	//通过流输出
    	 		if(index>-1) {	
    	 			out.print("true");
    	 		   l.setUser_state("成功");
          			l.setLog_content("【"+u.getRole()+u.getUsername()+"】编辑id为：["+data.getString("id")+"]的用户成功");
    	 			}
    	 		else {
    	 		   l.setUser_state("失败");
          			l.setLog_content("【"+u.getRole()+u.getUsername()+"】编辑id为：["+data.getString("id")+"]的用户失败");
    	 			out.print("false");
    	 		}
          	   	out.flush();
          	   	out.close();
    	 		
    	 		
    	 	
    	 		
    	 		
    	 		
    	 		
    	 	}else if("findUser".equals(action)) {
    	 	 	  l.setLog_action("查找用户");

    	 		String username=req.getParameter("username");
    	 		List<User> user=us.findUser(username);
    	 	System.out.println(username);
          	   System.out.println(user);
          	   //发回处理结果
          	   	//通过流输出
          	   	out.print(new Gson().toJson(user) );;
          	   	out.flush();
          	   	out.close();
    	 		
    	 		
    	 	}else if("stopUser".equals(action)) {
  	 	 	  l.setLog_action("停用用户");

    	 		  int id=Integer.parseInt(req.getParameter("id"));
    	 		  int state=Integer.parseInt(req.getParameter("state"));
       		   System.out.println(id);
       		   int index=us.stopUser(id,state);
       		   System.out.println(index);
       		  if(index>0) {
        			out.print("true" );;
            	   	out.flush();
            	   	out.close();
            	    l.setUser_state("成功");
           			l.setLog_content("【"+u.getRole()+u.getUsername()+"】停用id为：["+id+"]的用户成功");}
       	   l.setUser_state("失败");
  			l.setLog_content("【"+u.getRole()+u.getUsername()+"】失败id为：["+id+"]的用户成功");
       		
    	 		
    	 	}else if("deletUser".equals(action)) {
  	 	 	  l.setLog_action("删除用户");

    		   int id=Integer.parseInt(req.getParameter("id"));
    		   System.out.println(id);
    		   int index=us.deletUser(id);
    		   System.out.println(index);
    		   if(index>0) {
         			out.print("true" );;
             	   	out.flush();
             	   	out.close();
             	   l.setUser_state("成功");
       			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为：["+id+"]的用户成功");
    		   }
    		   l.setUser_state("失败");
      			l.setLog_content("【"+u.getRole()+u.getUsername()+"】删除id为：["+id+"]的用户失败");

    	   }else if("allUser".equals(action)) {
    		   
        	   List<User> uList=us.allUser();
        	   System.out.println(uList);
        	   //发回处理结果
        	   	//通过流输出
        	   	out.print(new Gson().toJson(uList) );;
        	   	out.flush();
        	   	out.close();
    	   }
    	
    		//将log写进数据库中去
			int index1=ls.addLog(l);
			if(index1>0) {
				System.out.println("Admin日志写入成功");
			}
    }
  
}
