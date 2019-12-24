package com.simtekgamedevelopment.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class HunterDbUtils {
	private DataSource dbSrc;

	public HunterDbUtils(DataSource dbSrc) {
		this.dbSrc = dbSrc;
	}
	
	public List<Hunter> getHunters() {
		List<Hunter> hunterArr = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbSrc.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from public.\"Hunters\"";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				String email = rs.getString("email");
				long id = rs.getLong("id");
				Hunter hunter = new Hunter(id, fname, lname, email);
				hunterArr.add(hunter);
				
			}
			return hunterArr;
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			close(conn, stmt, rs);
		}
		return null;
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (rs != null) {
			rs.close();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addHunter(Hunter hunter) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dbSrc.getConnection();
		String sql = "Insert into public.\"Hunters\" (id, firstname, lastname, email) " + "values (?, ?, ?, ?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setLong(1, hunter.getId());
				myStmt.setString(2, hunter.getFname());
				myStmt.setString(3, hunter.getLname());
				myStmt.setString(4, hunter.getEmail());
				myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}		
	}	
}
