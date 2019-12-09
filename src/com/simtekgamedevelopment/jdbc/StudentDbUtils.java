package com.simtekgamedevelopment.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.simtekgamedevelopment.Student;

public class StudentDbUtils {
	private DataSource ds;

	public StudentDbUtils(DataSource ds) {
		this.ds = ds;
	}

	public List<Student> getStudents() throws SQLException {
		ArrayList<Student> arr = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from public.\"Hunters\"";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				String email = rs.getString("email");
				long id = rs.getLong("id");
				Student student = new Student(id, fname, lname, email);
				arr.add(student);
				
			}
			return arr;
		} catch(Exception e) {
			e.getMessage();
		} finally {
			close(conn, stmt, rs);
		}
	
		
		return null;
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (rs != null) {
			rs.close();
		}
	}

}
