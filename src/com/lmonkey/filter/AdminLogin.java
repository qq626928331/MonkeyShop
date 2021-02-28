package com.lmonkey.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/**
 * Servlet Filter implementation class AdminLogin
 */
@WebFilter("/manage/*")
public class AdminLogin implements Filter {

    /**
     * Default constructor. 
     */
    public AdminLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;	
		
		//设置字符集
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		HttpSession session = req.getSession();

		String flag = (String) session.getAttribute("isAdminLogin");
		
		String request_uri = req.getRequestURI();			
		String ctxPath = req.getContextPath();
		String uri = request_uri.substring(ctxPath.length());
		
		if (uri.contains("admin_login.jsp") || uri.contains("adminlogin")) {
			chain.doFilter(req, resp);
		} else {
			if (flag != null && flag.equals("1")) {
				chain.doFilter(req, resp);
			} else {
				PrintWriter out = resp.getWriter();
				out.write("<script>");
				out.write("alert('请先登录！');");
				out.write("location.href='admin_login.jsp';");
				out.write("</script>");
				out.close();
			}
		}
		
		return;
		
		/*if (!sysCode.equals(verycode)) {
			out.write("<script>");
			out.write("alert('验证码输入有误');");
			out.write("location.href='reg.jsp';");
			out.write("</script>");
			out.close();
			return;
		}
		
		//不通过则直接return
		chain.doFilter(req, resp); //通过则使用这条数据*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
