



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
import dao.UserDAO;
import model.Film;
import model.Korisnik;
import model.Korisnik.Role;




/**
 * Servlet implementation class FilmoviServlet
 */
public class FilmoviServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmoviServlet() {
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
			}} catch (Exception ex) {}
		try {
			String name = request.getParameter("nameFilter");
			name = (name != null? name: "");
			List<Film> filmovi = FilmDAO.getAll(name);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filmovi", filmovi);

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
				String id= request.getParameter("id");
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

				Film film = new Film(id, naziv, reziser,glumci,zanr,trajanje,distributer,zemlja,godina,opis);
				
				try {
					FilmDAO.add(film);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				break;
			}
			case "update": {
				String id = request.getParameter("id");
				Film film =FilmDAO.get(id);

				String naziv = request.getParameter("naziv");
				naziv = (!"".equals(naziv)? naziv: film.getNaziv());
				String reziser = request.getParameter("reziser");
				naziv = (!"".equals(reziser)? naziv: film.getReziser());
				String glumci = request.getParameter("glumci");
				glumci = (!"".equals(glumci)? glumci: film.getGlumci());
				String zanr = request.getParameter("zanr");
				zanr = (!"".equals(zanr)? zanr: film.getZanrovi());
				String distributer = request.getParameter("distributer");
				distributer = (!"".equals(distributer)? naziv: film.getDistribuer());
				String zemlja = request.getParameter("zemlja");
				zemlja = (!"".equals(zemlja)? zemlja: film.getZemljaPorekla());
				String opis = request.getParameter("opis");
				opis = (!"".equals(opis)? opis: film.getOpis());
				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				trajanje = (trajanje > 0? trajanje: film.getTrajanje());
				int godina = Integer.parseInt(request.getParameter("godina"));
				godina = (godina > 0? godina: film.getGodina());
				film.setNaziv(naziv);
				film.setDistribuer(distributer);
				film.setGlumci(glumci);
				film.setZanrovi(zanr);
				film.setReziser(reziser);
				film.setZemljaPorekla(zemlja);
				film.setOpis(opis);
				film.setGodina(godina);
				film.setTrajanje(trajanje);
				
				FilmDAO.update(film);
				break;
			}
			case "delete": {
				String id = request.getParameter("id");
				FilmDAO.delete(id);
				break;
			}
		
	}

		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
	} catch (Exception ex) {
		ex.printStackTrace();
		request.getRequestDispatcher("./FailureServlet").forward(request, response);
	}
	}
	}


