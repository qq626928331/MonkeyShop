package com.lmonkey.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/manage/adminlogin")
public class AdminLogin extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");

		int count = LMONKEY_USERDao.selectByNM(username, password);
		
		System.out.println(username+"##"+password);

		if (count > 0) {//登录成功
			
			LMONKEY_USER user = LMONKEY_USERDao.selectAdmin(username, password);
			HttpSession session = request.getSession(); 
			session.setAttribute("name", user);
			session.setAttribute("isLogin", "1");
			if (user.getUSER_STATUS()==2) {
				session.setAttribute("isAdminLogin", "1");
				response.sendRedirect("/MonkeyShop/manage/admin_index.jsp");
			} else {
				response.sendRedirect("/MonkeyShop/index.jsp");
			}

			

		}else{//登录失败
			PrintWriter out = response.getWriter();

			out.write("<script>");
			out.write("alert('用户登录失败！');");
			out.write("location.href='admin_login.jsp';");
			out.write("</script>");
			out.close();
		}
	}

}
