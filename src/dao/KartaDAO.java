package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Karta;
import model.Projekcija;
import dao.ConnectionManager;
public class KartaDAO {

	
	public static Karta get(String id) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM karta WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				 int index = 1;
				 id = rset.getString(index++);
				 String projekcija = rset.getString(index++);
				 String sediste = rset.getString(index++);
				 String datum = rset.getString(index++);
				 String user = rset.getString(index++);

		         Karta karta= new Karta(id,projekcija,sediste,datum,user);
		         return karta;
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
			}
		
		return null;
	}
	public static boolean add(Karta karta) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO karta (id, projekcija, sediste, datum, user) "
					+ "VALUES (?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, karta.getId());
			pstmt.setString(index++, karta.getProjekcija());
			pstmt.setString(index++, karta.getSediste());
			pstmt.setString(index++, karta.getDatum());
			pstmt.setString(index++, karta.getKorisnik());
			
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
			}
	}
	public static List<Karta> getAll(String name) throws Exception {
		List<Karta> karte = new ArrayList<>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM karta WHERE projekcija LIKE ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			name="%"+name+"%";
			pstmt.setString(index++, name);
			
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				index = 1;
				 String id = rset.getString(index++);
				 String projekcija = rset.getString(index++);
				 String sediste = rset.getString(index++);
				 String datum = rset.getString(index++);
				 String user = rset.getString(index++);

		         Karta karta= new Karta(id,projekcija,sediste,datum,user);
		        
		         karte.add(karta);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
			}
		
		return karte;
	}
}
