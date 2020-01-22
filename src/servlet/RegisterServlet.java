package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Korisnik;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userName = request.getParameter("userName");
			if (UserDAO.get(userName) != null)
				throw new Exception("Korisnicko ime vec postoji!");
			if ("".equals(userName))
				throw new Exception("Korisnicko ime je prazno!");

			String password = request.getParameter("password");
			if ("".equals(password))
				throw new Exception("Lozinka je prazna!");
			
			String datum = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Korisnik user = new Korisnik(userName, password, datum,Role.USER);
			UserDAO.add(user);


			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			String message = ex.getMessage();
			if (message == null) {
				message = "Nepredvidjena greska!";
				ex.printStackTrace();
			}


			Map<String, Object> data = new LinkedHashMap<>();
			data.put("message", message);

			request.setAttribute("data", data);
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	}

}
