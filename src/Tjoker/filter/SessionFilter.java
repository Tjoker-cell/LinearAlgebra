package Tjoker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import Tjoker.pojo.User;

public class SessionFilter implements Filter {
//判断session是否失效，如果是失效则跳转到登录界面只拦截jsp
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//判断session是否失效
		//获取访问的URL
		String strURL=((HttpServletRequest)request).getRequestURL().toString();
		//登录界面不用过滤
			if(strURL.indexOf("login.jsp")==-1) {
				HttpSession hs=((HttpServletRequest)request).getSession();
				User user=(User) hs.getAttribute("user");
				if(user==null) {
					JOptionPane.showMessageDialog(null, "用户未登录或登录失效请登录");
					//请求转发到登录界面
					request.getRequestDispatcher("/client/login.jsp").forward(request, response);
					return;
				}
			
				
			}
			chain.doFilter(request, response);
	}
}
