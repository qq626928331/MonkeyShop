package com.lmonkey.servlet.cate;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.service.LMONKEY_CATEGORYDao;

/**
 * Servlet implementation class ToCateUpdate
 */
@WebServlet("/manage/admin_tocateupdate")
public class ToCateUpdate extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		LMONKEY_CATEGORY category = LMONKEY_CATEGORYDao.selectById(id);
		
		ArrayList<LMONKEY_CATEGORY> catelist = LMONKEY_CATEGORYDao.selectAll();

		request.setAttribute("catelist", catelist);
		
		request.setAttribute("category", category);


		request.getRequestDispatcher("admin_catemodify.jsp").forward(request, response);
	}

	

}
