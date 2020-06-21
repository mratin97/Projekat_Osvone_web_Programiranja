package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmDAO;
import dao.KartaDAO;
import dao.UserDAO;
import model.Film;
import model.Karta;
import model.Korisnik;

/**
 * Servlet implementation class KartaPregledServlet
 */
public class KartaPregledServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KartaPregledServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String loggedInUserName = (String) request.getSession().getAttribute("loggedInUserName");
		
		if (loggedInUserName == null) {

			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		try {
			Korisnik loggedInUser = UserDAO.get(loggedInUserName);
			if (loggedInUser == null) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}} catch (Exception ex) {}
		try {
			
			List<Karta> karte = KartaDAO.getAllbyKorisnik(loggedInUserName);
			List<Karta> karte2 = KartaDAO.getAll("");
			
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("karte", karte);
			data.put("karte2", karte2);
			System.out.println(karte);
			Korisnik loggedInUser = UserDAO.get(loggedInUserName);
			data.put("loggedInUserRole", loggedInUser.getRole());
			data.put("loggedInUserName", loggedInUser.getId());
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
