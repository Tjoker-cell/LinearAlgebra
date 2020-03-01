package Tjoker.client.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.omg.SendingContext.CodeBasePackage.URLHelper;

import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.Log;
import Tjoker.pojo.User;
import Tjoker.service.FileService;
import Tjoker.service.LogService;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
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
	 	  l.setLog_action("下载文件");
    		String id=req.getParameter("id");
    			//获取service层
    		FileService  fs=new FileService();
    		FileUpDown fup=fs.downloadFileService(id);
    		
    		String path=fup.getSavepath();//保存路径
    		String uname=fup.getUuidname();//保存文件名
    		File f=new File(path, uname);//要下载的文件的存放位置    path/name
    		
    		//健壮性判断
    		if(!f.exists()){
    			l.setUser_state("失败");
    			l.setLog_content("【"+u.getRole()+u.getUsername()+"】下载文件失败原因可能为：文件已删除");
    			req.setAttribute("message", "对不起，当前文件已删除");
    			req.getRequestDispatcher("/message.jsp").forward(req, resp);
    			//将log写进数据库中去
    			int index1=ls.addLog(l);
    			if(index1>0) {
    				System.out.println("DownloadServlet日志写入失败");
    			}
    		}
    		
    		//将中文的文件名编码后再放到http的响应头中去，编码之后浏览器收到后会自动解码
    			String fileName=URLEncoder.encode(fup.getFilename(),"utf-8");
    		//设置参数是浏览器可以以下载的方式打开文件
    			resp.setHeader("content-disposition", "attachement;fileName="+fileName);
    		//将要下载的文件已inputstream读取进来
    			InputStream in=new FileInputStream(f);
    		//读取进来后就写到resp中的getOutStream中去
    			OutputStream out=resp.getOutputStream();
    			
    			byte[] buf=new byte[1024];
    			int len=0;
    			while ((len=in.read(buf))!=-1) {
					out.write(buf,0,len);
					
				}
    			l.setUser_state("成功");
    			l.setLog_content("【"+u.getRole()+u.getUsername()+"】下载文件id:["+id+"]成功");
    			in.close();
    			out.close();
    			System.out.println("文件下载成功");
    			//将log写进数据库中去
    			int index1=ls.addLog(l);
    			if(index1>0) {
    				System.out.println("DownloadServlet日志写入成功");
    			}
    			
    	//处理请求信息
    	//响应处理结果
    	
    	
    	
    	
    	
    	
    	}
}
