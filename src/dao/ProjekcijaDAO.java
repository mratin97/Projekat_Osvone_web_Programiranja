package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Projekcija;
import dao.ConnectionManager;


public class ProjekcijaDAO {
		public static Projekcija get(String id) throws Exception {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT * FROM projekcija WHERE id = ?";

				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, id);
				System.out.println(pstmt);

				rset = pstmt.executeQuery();

				if (rset.next()) {
					 int index = 1;
					 id = rset.getString(index++);
					 String idFilma = rset.getString(index++);
			         String tip = rset.getString(index++);
			         String sala = rset.getString(index++);
			         String datum = rset.getString(index++);
			         String vreme = rset.getString(index++);
			         int cena = rset.getInt(index++);
			         String adminId = rset.getString(index++);
			    

			         Projekcija projekcija=new Projekcija(id,idFilma,tip,sala,datum,vreme,cena,adminId);
			         return projekcija;
				}
			} finally {
				try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
				}
			
			return null;
		}
		public static boolean add(Projekcija projekcija) throws Exception {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			try {
				String query = "INSERT INTO projekcija (id,film,tip,sala,datum,vreme,cena,admin) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				pstmt = conn.prepareStatement(query);
				int index = 1;
				pstmt.setString(index++, projekcija.getId());
				pstmt.setString(index++, projekcija.getIdFilma());
				pstmt.setString(index++, projekcija.getTip());
				pstmt.setString(index++, projekcija.getSala());
				pstmt.setString(index++, projekcija.getDatum());
				pstmt.setString(index++, projekcija.getVreme());
				pstmt.setInt(index++, projekcija.getCena());
				pstmt.setString(index++, projekcija.getAdminId());
				System.out.println(pstmt);

				return pstmt.executeUpdate() == 1;
			} finally {
				try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
				}
		}
		public static List<Projekcija> getAll(String name) throws Exception {
			List<Projekcija> projekcije = new ArrayList<>();

			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT * FROM projekcija WHERE film LIKE ?";
				pstmt = conn.prepareStatement(query);
				int index = 1;
				name="%"+name+"%";
				pstmt.setString(index++, name);
				
				System.out.println(pstmt);

				rset = pstmt.executeQuery();

				while (rset.next()) {
					index = 1;
					 String id = rset.getString(index++);
					 String idFilma = rset.getString(index++);
			         String tip = rset.getString(index++);
			         String sala = rset.getString(index++);
			         String datum = rset.getString(index++);
			         String vreme = rset.getString(index++);
			         int cena = rset.getInt(index++);
			         String adminId = rset.getString(index++);

			         Projekcija projekcija=new Projekcija(id,idFilma,tip,sala,datum,vreme,cena,adminId);
			         projekcije.add(projekcija);
				}
			} finally {
				try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
				}
			
			return projekcije;
		}
		public static List<Projekcija> getAllbyId(String name) throws Exception {
			List<Projekcija> projekcije = new ArrayList<>();

			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT * FROM projekcija WHERE id LIKE ?";
				pstmt = conn.prepareStatement(query);
				int index = 1;
				name="%"+name+"%";
				pstmt.setString(index++, name);
				
				System.out.println(pstmt);

				rset = pstmt.executeQuery();

				while (rset.next()) {
					index = 1;
					 String id = rset.getString(index++);
					 String idFilma = rset.getString(index++);
			         String tip = rset.getString(index++);
			         String sala = rset.getString(index++);
			         String datum = rset.getString(index++);
			         String vreme = rset.getString(index++);
			         int cena = rset.getInt(index++);
			         String adminId = rset.getString(index++);

			         Projekcija projekcija=new Projekcija(id,idFilma,tip,sala,datum,vreme,cena,adminId);
			         projekcije.add(projekcija);
				}
			} finally {
				try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
				}
			
			return projekcije;
		}
		public static List<Projekcija> getAllDanas(String datum) throws Exception {
			List<Projekcija> projekcije = new ArrayList<>();

			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT * FROM projekcija WHERE datum LIKE ? order by film,vreme";
				pstmt = conn.prepareStatement(query);
				int index = 1;
				
				pstmt.setString(index++, datum);
				
				System.out.println(pstmt);

				rset = pstmt.executeQuery();

				while (rset.next()) {
					index = 1;
					 String id = rset.getString(index++);
					 String idFilma = rset.getString(index++);
			         String tip = rset.getString(index++);
			         String sala = rset.getString(index++);
			         datum=rset.getString(index++);
			         String vreme = rset.getString(index++);
			         int cena = rset.getInt(index++);
			         String adminId = rset.getString(index++);

			         Projekcija projekcija=new Projekcija(id,idFilma,tip,sala,datum,vreme,cena,adminId);
			         projekcije.add(projekcija);
				}
			} finally {
				try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
				}
			
			return projekcije;
		}
		public static int count(String name) throws Exception {
			

			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int count;
			try {
				String query = "SELECT COUNT(film) FROM projekcija WHERE film= ?";
				pstmt = conn.prepareStatement(query);
				int index = 1;
				name="%"+name+"%";
				pstmt.setString(index++, name);
				
				System.out.println(pstmt);

				rset = pstmt.executeQuery();
				count = rset.getInt(index++);
				
			} finally {
				try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
				}
			
			return count;
		}
		public static boolean update(Projekcija projekcija) throws Exception {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = null;
			try {
				String query = "UPDATE projekcija SET film=?,tip=?,sala=?,datum=?,vreme=?,cena=?,admin=?  "
						+ "WHERE id = ?";

				pstmt = conn.prepareStatement(query);
				int index = 1;
			
				pstmt.setString(index++, projekcija.getId());
				pstmt.setString(index++, projekcija.getIdFilma());
				pstmt.setString(index++, projekcija.getTip());
				pstmt.setString(index++, projekcija.getSala());
				pstmt.setString(index++, projekcija.getDatum());
				pstmt.setString(index++, projekcija.getVreme());
				pstmt.setInt(index++, projekcija.getCena());
				pstmt.setString(index++, projekcija.getAdminId());
				
				System.out.println(pstmt);

				return pstmt.executeUpdate() ==  1;
			} finally {
				try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
			}
		}
}
