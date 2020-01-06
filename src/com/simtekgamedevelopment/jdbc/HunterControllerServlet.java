package com.simtekgamedevelopment.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
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
		try {
			String command = request.getParameter("command");
			System.out.println("!!!!!!!");
			if (command == null) {
				command = "LIST";
			}
			switch(command) {
			case "LIST":
				listHunters(request, response);
				break;
			case "ADD":
				addHunter(request, response);
				break;	
			case "UPDATE":
				updateHunter(request, response);
				break;
			default:
				listHunters(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	private void updateHunter(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addHunter(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("I am in addhUNTER");
		String firstName = request.getParameter("firstName");
		System.out.println("firstName = " + firstName);
		String lastName = request.getParameter("lastName");
		System.out.println("lastName = " + lastName);
		String email = request.getParameter("email");
		Hunter hunter = new Hunter(System.currentTimeMillis(), firstName, lastName, email);
		dbUtil.addHunter(hunter);
		listHunters(request, response);
		
	}

	private void listHunters(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Hunter> hunters = dbUtil.getHunters();
		request.setAttribute("HUNTER_LIST", hunters);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-hunters.jsp");
		dispatcher.forward(request, response);
	} 
}
