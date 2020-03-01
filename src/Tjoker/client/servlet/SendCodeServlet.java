package Tjoker.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;

import Tjoker.pojo.Log;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import Tjoker.service.UserService;


/**
 * Servlet implementation class SendCode
 */
public class SendCodeServlet extends HttpServlet {
	@SuppressWarnings("unlikely-arg-type")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取请求信息
		//申明日志对象实体类对象
		   Log l=new Log();
		   	//service层对象
		   LogService ls=new LogService();
			String eaddress=req.getParameter("eadress");
			String code=req.getParameter("code");
			String pore=req.getParameter("pore");
			System.out.println(pore+"=pore");
			PrintWriter out=resp.getWriter();
			if("reg".equals(pore)) {
				//调用service层对象
				UserService us=new UserService();
				User u=us.checkEmailService(eaddress);
				if(u!=null) {
					out.write("0");
				}
				return ;
			}
		//处理请求信息
			System.out.println("afsnglbadfdjgb");
			HttpSession hs=req.getSession();
			hs.setAttribute("emailAddress", eaddress);
			System.out.println(eaddress+"::::::"+code);
			//通过输出流进行返回
		
			boolean bl;
			bl=sendEmail(code, eaddress);
			System.out.println("SendCode.service()"+bl);
			if(bl==true) {
				out.write("true");
			}else{
				out.write("false");
			}
		//响应处理结果
		
	}
//邮箱验证
	private boolean sendEmail(String code, String eaddress) {
		try {
			
			HtmlEmail email = new HtmlEmail();//不用更改
			email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
			email.setCharset("UTF-8");
			email.addTo(eaddress);// 收件地址
 
			email.setFrom("*********@qq.com", "Tjoker线代学习");//此处填邮箱地址和用户名,用户名可以任意填写
 
			email.setAuthentication("**********@qq.com", "vqavqdiltaokbcbd");//此处填写邮箱地址和客户端授权码
 
			email.setSubject("Tjoker");//此处填写邮件名，邮件名可任意填写
			email.setMsg("【小凡Tjoker】:" + code+"(邮箱验证码)，请勿转发，否则会导致账户被盗。");//此处填写邮件内容
 
			email.send();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

}
}