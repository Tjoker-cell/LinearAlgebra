package Tjoker.admin.servlet;


import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.Log;
import Tjoker.service.FileService;
import Tjoker.service.LogService;

public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
		//申明日志对象实体类对象
		   Log l=new Log();
		   	//service层对象
		   LogService ls=new LogService();
		public void init() throws ServletException {
			//获取service层对象到
			FileService fs=new FileService();
			List<FileUpDown> list=fs.list();
			ServletContext sc=this.getServletContext();
			sc.setAttribute("list", list);
			System.out.println(list);
		}
}
