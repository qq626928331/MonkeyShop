package com.lmonkey.servlet.cate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_CATEGORYDao;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class DoUserCate
 */
@WebServlet("/manage/admin_docateadd")
public class DoUserCate extends HttpServlet {
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		int fid = Integer.parseInt(request.getParameter("parentId"));
		String name = request.getParameter("className");
		
		//创建分类实体
		LMONKEY_CATEGORY category = new LMONKEY_CATEGORY(0, name, fid);

		//加入到数据库的用户表中
		LMONKEY_CATEGORYDao.insert(category);

		//成功或失败重定向到哪里


		response.sendRedirect("admin_cateselect");

		PrintWriter out = response.getWriter();

		out.write("<script>");
		out.write("alert('分类添加失败');");
		out.write("location.href='manage/admin_useradd.jsp';");
		out.write("</script>");

		


	}

}
