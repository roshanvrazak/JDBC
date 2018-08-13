package com.caps.jsp;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class PreparedStatementJDBC {

	public static void main(String[] args) {
		// JDBC PreparedStatement 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db";
		String qry = "select * from students_info where sid=?";
		try {
			FileReader fr = new FileReader("lib/db.properties");
			Properties prop = new Properties();
			prop.load(fr);

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(dbUrl, prop);

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter student id: ");
			int id = Integer.parseInt(sc.nextLine());
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int reg = rs.getInt("sid");
				String fnam = rs.getString("firstname");
				String lnam = rs.getString("lastname");
				String isAdm = (rs.getString("isadmin")).toUpperCase();
				String pass = rs.getString("password");

				System.out.println("sid : " + reg);
				System.out.println("fname : " + fnam);
				System.out.println("lname : " + lnam);
				System.out.println("Admin : " + isAdm);
				System.out.println("password : " + pass);
			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
