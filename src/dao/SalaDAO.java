package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Sala;
import dao.ConnectionManager;
public class SalaDAO {

	
	public static Sala get(String id) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM sala WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				 int index = 1;
				 id = rset.getString(index++);
				 String naziv = rset.getString(index++);
				 String tip = rset.getString(index++);

		         Sala sala= new Sala(id,naziv,tip);
		         return sala;
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
			}
		
		return null;
	}
	
}
