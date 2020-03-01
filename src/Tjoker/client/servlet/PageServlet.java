package Tjoker.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.org.apache.bcel.internal.generic.NEW;

import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.Log;
import Tjoker.pojo.Notice;
import Tjoker.pojo.Page;
import Tjoker.service.FileService;
import Tjoker.service.LogService;
import Tjoker.service.NoticeService;
import Tjoker.service.PageService;

/**
 * Servlet implementation class PageServlet
 */
public class PageServlet extends HttpServlet {
//利用json，getJson将对象通过json形式传到jsp
	private static final long serialVersionUID = 1L;
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//申明日志对象实体类对象
 	   Log l=new Log();
 	   	//service层对象
 	   LogService ls=new LogService();
    	//获取请求信息（当前页数，	
    	String action=req.getParameter("action");
    	System.out.println(action);
    	 int cPage=Integer.parseInt(req.getParameter("currentPage"));//强制转换为int
    	 String searchString=req.getParameter("search");
    
//    	 searchString=(searchString=="")?"notfind":searchString;//三目运算进行赋值
    	 Page p=new Page();
    	 System.out.println("currentPage:"+cPage);
     	int  pageSize=3;//设置当前页面显示条数
    	 //获取service层对象
    	  PageService ps=new PageService();
    	  FileService fs=new FileService();
    	  NoticeService ns=new NoticeService();
    	  if("notice".equals(action)) {
    		  List<Notice> listPage=ns.getCurrentNotice(cPage,pageSize,searchString);
        	  List<Notice> list=ns.allNotice(searchString);
    		  int totalNum=list.size();//获取数据量总数	
    		  System.out.println("数据总数："+totalNum);
  	  		System.out.println("cPage,pageSize:"+cPage+";"+pageSize);
  	  		p.setTotalnum(totalNum);
  	  		p.setN(listPage);
  	  		System.out.println("总页数："+p.getTotalPage());
  	  		System.out.println("分页数据："+listPage);
  	  		System.out.println("PageServlet.service()listPage:"+listPage);
    	  } else {
    		  List<FileUpDown> listPage=ps.getCurrentFile(cPage,pageSize,searchString);
    	  List<FileUpDown> list=ps.list(searchString);
    	  int totalNum=list.size();//获取数据量总数	
    	  System.out.println("数据总数："+totalNum);
	  		System.out.println("cPage,pageSize:"+cPage+";"+pageSize);
	  		p.setTotalnum(totalNum);
	  		p.setF(listPage);
	  		System.out.println("总页数："+p.getTotalPage());
	  		System.out.println("分页数据："+listPage);
	  		System.out.println("PageServlet.service()listPage:"+listPage);
    	  }
    	 
    	  
    	  	p.setCurrentPage(cPage);
    		p.setPageSize(pageSize);
    		PrintWriter out=resp.getWriter();
    		out.print(new Gson().toJson(p));
    		out.flush();//强制刷出缓冲池的数据
    		out.close();
    	
    	}
    
    
  
}
