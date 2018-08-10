package com.caps.jsp;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class MyFirstJDBCProgramV3 {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			java.sql.Driver d = new Driver();
			DriverManager.registerDriver(d);
			System.out.println("Driver Loaded !!!");

			FileReader fr = new FileReader("lib/db.properties");
			Properties prop = new Properties();
			prop.load(fr);

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/capsV3_db", prop);
			System.out.println("Connection Established");

			stmt = con.createStatement();
			System.out.println("Statement created");
			rs = stmt.executeQuery("select * from capsV3_db.students_info");

			while (rs.next()) {
				int sid = rs.getInt("sid");
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				String isadmin = rs.getString("isadmin");
				String pass = rs.getString("password");

				System.out.println(sid);
				System.out.println(fname);
				System.out.println(lname);
				System.out.println(isadmin);
				System.out.println(pass);
				System.out.println("-------------------------------");
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
