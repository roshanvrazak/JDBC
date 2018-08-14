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

public class UpdatePasswordJDBC {

	public static void main(String[] args) throws Exception {
		// JDBC PreparedStatement
		Connection con = null;
		PreparedStatement pstmt = null;
		//PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		//ResultSet rs2 = null;
		String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter sid : ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Enter old password: ");
		String oldpass=sc.nextLine();
		System.out.println("Enter New Password: ");
		String p1 = sc.nextLine();
		System.out.println("Confirm new Password: ");
		String p2 = sc.nextLine();
		
		if (p1.equals(p2)) {
			System.out.println("passwords correct");
			try {
				FileReader fr = new FileReader("lib/db.properties");
				Properties prop = new Properties();
				prop.load(fr);
				String qry = "update students_info set password=? where sid=? and password=?";
				//String old="select password from students_info where sid=?";
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(dbUrl, prop);
				
				/*pstmt2=con.prepareStatement(old);
				pstmt2.setInt(1, id);
				rs2=pstmt2.executeQuery();
				while(rs2.next()) {
					oldpass=rs2.getString("password");
				}*/

				pstmt = con.prepareStatement(qry);
				pstmt.setString(1,p1);
				pstmt.setInt(2, id);
				pstmt.setString(3, oldpass);

				int count= pstmt.executeUpdate();

				if(count > 0) {
					System.out.println("Password Updated...");
				}else {
					System.out.println("Password Updation Failed");
				}

			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			} finally {
				
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
		} else {
			throw new Exception("Passwords Doesn't Match");
		}

	}

}
