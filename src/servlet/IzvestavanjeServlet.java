package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmDAO;
import dao.KartaDAO;
import dao.ProjekcijaDAO;
import dao.UserDAO;
import model.Film;
import model.Izvestaj;
import model.Korisnik;
import model.Projekcija;

/**
 * Servlet implementation class IzvestavanjeServlet
 */
public class IzvestavanjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzvestavanjeServlet() {
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
			Korisnik loggedInUser = UserDAO.get(loggedInUserName);
			List<Projekcija> projekcije = ProjekcijaDAO.getAll(name);
			List<Izvestaj> izvestaji = new ArrayList<Izvestaj>();
			Set<Izvestaj> izvestaji112 = new HashSet<Izvestaj>();
		
			
			for(Projekcija projekcija : projekcije) {
				int countP= ProjekcijaDAO.count(projekcija.getIdFilma());
				int countK= KartaDAO.count(projekcija.getIdFilma());
				int cena= countK * projekcija.getCena();
				Izvestaj izvestaj= new Izvestaj(projekcija.getIdFilma(),countP,countK,cena);
				izvestaji.add(izvestaj);
		
				
				
						
					
				
				
			
			}
		
			data.put("izvestaji", izvestaji);
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
