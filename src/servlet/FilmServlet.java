package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import dao.FilmDAO;
import model.Film;
import dao.UserDAO;
import model.Korisnik;
import model.Korisnik.Role;;


/**
 * Servlet implementation class FilmServlet
 */

public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			}
				String id = request.getParameter("id");
				Map<String, Object> data = new LinkedHashMap<>();
				try {
					Film film= FilmDAO.get(id);
					data.put("film", film);
					request.setAttribute("data", data);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			}
			if (loggedInUser.getRole() != Role.ADMIN) {

				request.getRequestDispatcher("./UnauthorizedServlet").forward(request, response);
				return;
			}

		
		String action = request.getParameter("action");
		
		switch (action) {
			case "add": {
				String naziv = request.getParameter("naziv");
				naziv = (!"".equals(naziv)? naziv: "<prazan naziv>");
				String reziser = request.getParameter("reziser");
				reziser = (!"".equals(reziser)? reziser: "<prazan reziser>");
				String glumci = request.getParameter("glumci");
				glumci = (!"".equals(glumci)? glumci: "<prazan glumci>");
				String zanr = request.getParameter("zanr");
				zanr = (!"".equals(zanr)? zanr: "<prazan zanr>");
				String distributer = request.getParameter("distributer");
				distributer = (!"".equals(distributer)? distributer: "<prazan distributer>");
				String zemlja = request.getParameter("zemlja");
				zemlja = (!"".equals(zemlja)? zemlja: "<prazan zemlja>");
				String opis = request.getParameter("opis");
				opis = (!"".equals(opis)? opis: "<prazan opis>");
				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				trajanje = (trajanje > 0? trajanje: 3000);
				int godina = Integer.parseInt(request.getParameter("godina"));
				godina = (godina > 0? godina: 3000);
				String id= request.getParameter("id");
				Film film = new Film(id, naziv, reziser,glumci,zanr,trajanje,distributer,zemlja,godina,opis);
				
				try {
					FilmDAO.add(film);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				break;
			}
			
	}

		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
	} catch (Exception ex) {
		ex.printStackTrace();
		request.getRequestDispatcher("./FailureServlet").forward(request, response);
	}
	}}
