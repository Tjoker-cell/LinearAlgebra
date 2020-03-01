package Tjoker.admin.servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.mail.handlers.multipart_mixed;

import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.Log;
import Tjoker.pojo.User;
import Tjoker.service.FileService;
import Tjoker.service.LogService;
import Tjoker.utils.WebUtils;


public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   //判断是否是请求的表单信息
		//申明日志对象实体类对象
	   Log l=new Log();
	   	//service层对象
	   LogService ls=new LogService();
	   User u= (User)(req.getSession().getAttribute("user"));
	 	  l.setUser_id(u.getId());
	 	  l.setUser_name(u.getUsername());
	 	  l.setUser_role(u.getRole());
	 	  l.setLog_action("上传文件");
	 	  
	 	  
	   if(!ServletFileUpload.isMultipartContent(req)) {
			l.setUser_state("失败");
			l.setLog_content("【"+u.getRole()+u.getUsername()+"】上传文件失败");
		   req.setAttribute("message", "不是请求的表单信息，请确认表单属性是否正确");
		   req.getRequestDispatcher("message.jsp").forward(req, resp);
		   return;
	   }
	   try {
		   //调用工具类，获得上传文件信息
		   FileUpDown f=WebUtils.doFileUploda(req);
			FileService service=new FileService();
		int index=service.insert(f);//保存上传文件信息
		   if(index>=0) {
				l.setUser_state("成功");
				l.setLog_content("【"+u.getRole()+u.getUsername()+"】上传文件成功");
			req.setAttribute("message", "文件上传成功");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		   }else {
			   l.setUser_state("失败");
				l.setLog_content("【"+u.getRole()+u.getUsername()+"】上传文件失败可能原因为：文件大小超过了限制");
			   req.setAttribute("message", "对不起，您上传的文件大小超过了大小的限制");
				req.getRequestDispatcher("/message.jsp").forward(req,resp);
		}
		
	} catch (FileSizeLimitExceededException e) {
		
	}
	   
		//将log写进数据库中去
		int index1=ls.addLog(l);
		if(index1>0) {
			System.out.println("upload日志写入成功");
		}
	   }

}
