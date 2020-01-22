package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Korisnik;
import model.Korisnik.Role;
import dao.ConnectionManager;
import model.Korisnik;


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
	public static boolean add(Korisnik user) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO users (userName, password, role, date) "
					+ "VALUES (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getId());
			pstmt.setString(index++, user.getPass());
			pstmt.setString(index++, user.getRole().toString());
			pstmt.setString(index++, user.getDatum());
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
			}
	}
	public static List<Korisnik> getAll(String name) throws Exception {
		List<Korisnik> korisnici = new ArrayList<>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM users WHERE userName LIKE ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			name="%"+name+"%";
			pstmt.setString(index++, name);
			
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				index = 1;
				 String id = rset.getString(index++);
				 String pass = rset.getString(index++);
				 Role role=Role.valueOf(rset.getString(index++));
		         
		         String date = rset.getString(index++);
		        

		         Korisnik korisnik=new Korisnik(id,pass,date,role);
		         korisnici.add(korisnik);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
			}
		
		return korisnici;
	}
}
