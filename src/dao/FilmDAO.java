package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Film;

import dao.ConnectionManager;



public class FilmDAO {
	public static Film get(String id) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM filmovi WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				 int index = 1;
				 id = rset.getString(index++);
				 String naziv = rset.getString(index++);
		         String reziser = rset.getString(index++);
		         String glumci = rset.getString(index++);
		         String zanrovi = rset.getString(index++);
		         int trajanje = rset.getInt(index++);
		         String distributer = rset.getString(index++);
		         String zemlja = rset.getString(index++);
		         int godina = rset.getInt(index++);
		         String opis = rset.getString(index++);

		         Film film=new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer , zemlja, godina, opis);
		         return film;
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
			}
		
		return null;
	}
	public static boolean add(Film film) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO filmovi (naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godina, opis) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanrovi());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistribuer());
			pstmt.setString(index++, film.getZemljaPorekla());
			pstmt.setInt(index++, film.getGodina());
			pstmt.setString(index++, film.getOpis());
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
			}
	}
	public static List<Film> getAll(String name) throws Exception {
		List<Film> filmovi = new ArrayList<>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM filmovi WHERE naziv LIKE ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			name="%"+name+"%";
			pstmt.setString(index++, name);
			
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				index = 1;
				 String id = rset.getString(index++);
				 String naziv = rset.getString(index++);
		         String reziser = rset.getString(index++);
		         String glumci = rset.getString(index++);
		         String zanrovi = rset.getString(index++);
		         int trajanje = rset.getInt(index++);
		         String distributer = rset.getString(index++);
		         String zemlja = rset.getString(index++);
		         int godina = rset.getInt(index++);
		         String opis = rset.getString(index++);

		         Film film=new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer , zemlja, godina, opis);
		         filmovi.add(film);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
			}
		
		return filmovi;
	}
    }
    


