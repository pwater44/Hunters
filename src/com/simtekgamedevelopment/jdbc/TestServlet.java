package com.simtekgamedevelopment.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @Resource(name="jdbc/hunters")
       private DataSource dataSource;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from public.\"Hunters\"";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				out.println(fname + "|" + lname);
			}
			
		} catch(Exception e) {
			out.println("error = " + e.getMessage());
		}
	}

}
