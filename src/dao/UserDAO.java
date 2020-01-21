package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Korisnik;
import model.Korisnik.Role;


public class UserDAO {

	public static Korisnik get(String userName, String password) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT role FROM users WHERE userName = ? AND password = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, userName);
			pstmt.setString(index++, password);
			
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				Role role = Role.valueOf(rset.getString(1));
				String datum="1/20/2020";
				return new Korisnik(userName, password, datum,role);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
			}

		return null;
	}
	public static Korisnik get(String userName) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT password, role FROM users WHERE userName = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				String password = rset.getString(index++);
				Role role = Role.valueOf(rset.getString(index++));
				String datum=rset.getString(index++);

				return new Korisnik(userName, password,datum, role);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
			}

		return null;
	}
}
