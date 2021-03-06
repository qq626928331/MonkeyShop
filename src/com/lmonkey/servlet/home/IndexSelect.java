package com.lmonkey.servlet.home;

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
 * Servlet implementation class IndexSelect
 */
@WebServlet("/indexselect")
public class IndexSelect extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<LMONKEY_CATEGORY> fList = LMONKEY_CATEGORYDao.selectCate("father");	
		request.setAttribute("flist", fList);
		
		ArrayList<LMONKEY_CATEGORY> cList = LMONKEY_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", cList);

		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	

}
