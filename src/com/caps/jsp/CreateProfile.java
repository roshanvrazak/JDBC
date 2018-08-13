package com.caps.jsp;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class CreateProfile {

	public static void main(String[] args) {
		// saving user details into database permanently  

		Connection con = null;
		PreparedStatement pstmt = null;

		String dburl = "jdbc:mysql://localhost/capsV3_db";

		String qry = "insert into students_info values(?,?,?,?,?)";

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student id: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Enter first name: ");
		String fnam = sc.nextLine();
		System.out.println("Enter last name: ");
		String lnam = sc.nextLine();
		System.out.println("are you an Admin (Y?N) :");
		String isAd = sc.nextLine();
		System.out.println("Enter password: ");
		String pass = sc.nextLine();
		sc.close();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			FileReader fr = new FileReader("lib/db.properties");
			Properties prop = new Properties();
			prop.load(fr);

			con = DriverManager.getConnection(dburl, prop);

			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, id);
			pstmt.setString(2, fnam);
			pstmt.setString(3, lnam);
			pstmt.setString(4, isAd);
			pstmt.setString(5, pass);

			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("Details added to DataBase succesfully");
			} else {
				System.out.println("Operation Failed!!!");
			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
