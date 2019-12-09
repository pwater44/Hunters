package com.simtekgamedevelopment.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/HunterControllerServlet")
public class HunterControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HunterDbUtils dbUtil;
	@Resource(name = "jdbc/hunters")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			dbUtil = new HunterDbUtils(dataSource);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		listHunters(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void listHunters(HttpServletRequest request, HttpServletResponse response) {
		List<Hunter> hunters = dbUtil.getHunters();
	} 
}
