package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjekcijaDAO;
import dao.UserDAO;
import dao.FilmDAO;
import model.Projekcija;
import model.Korisnik;
import model.Korisnik.Role;
import model.Film;

/**
 * Servlet implementation class ProjekcijeServlet
 */
public class ProjekcijeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjekcijeServlet() {
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
			String datum = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			List<Projekcija> projekcijedanas = ProjekcijaDAO.getAllDanas(datum);
			List<Film> filmovi = FilmDAO.getAll("");
			String name = request.getParameter("nameFilter");
			name = (name != null? name: "");
			List<Projekcija> projekcije = ProjekcijaDAO.getAll(name);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("projekcije", projekcije);
			data.put("projekcijedanas", projekcijedanas);
			Korisnik loggedInUser = UserDAO.get(loggedInUserName);
			data.put("loggedInUserRole", loggedInUser.getRole());
			data.put("datum", datum);
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
				String tip = request.getParameter("tip");
				tip = (!"".equals(tip)? tip: "<prazan tip>");
				String sala = request.getParameter("sala");
				sala = (!"".equals(sala)? sala: "<prazan glumci>");
				String datum = request.getParameter("datum");
				datum = (!"".equals(datum)? datum: "<prazan datum>");
				String vreme = request.getParameter("vreme");
				vreme = (!"".equals(vreme)? vreme: "<prazan vreme>");
				int cena = Integer.parseInt(request.getParameter("cena"));
				cena = (cena > 0? cena: 3000);
				String adminId="a";
				
				Projekcija projekcija=new Projekcija(id,naziv,tip,sala,datum,vreme,cena,adminId);
				try {
					ProjekcijaDAO.add(projekcija);
					break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
			case "update": {
				String id = request.getParameter("id");
				Projekcija projekcija =ProjekcijaDAO.get(id);

				
				String naziv = request.getParameter("naziv");
				naziv = (!"".equals(naziv)? naziv: "<prazan naziv>");
				String tip = request.getParameter("tip");
				tip = (!"".equals(tip)? tip: "<prazan tip>");
				String sala = request.getParameter("sala");
				sala = (!"".equals(sala)? sala: "<prazan glumci>");
				String datum = request.getParameter("datum");
				datum = (!"".equals(datum)? datum: "<prazan datum>");
				String vreme = request.getParameter("vreme");
				vreme = (!"".equals(vreme)? vreme: "<prazan vreme>");
				int cena = Integer.parseInt(request.getParameter("cena"));
				cena = (cena > 0? cena: 3000);
				String adminId="a";
				projekcija.setIdFilma(naziv);
				projekcija.setTip(tip);
				projekcija.setSala(sala);
				projekcija.setDatum(datum);
				projekcija.setVreme(vreme);
				projekcija.setCena(cena);
				projekcija.setAdminId(adminId);
				projekcija.setId(id);
				ProjekcijaDAO.update(projekcija);
				break;
				
			}
			case "delete": {
				String id = request.getParameter("id");
				ProjekcijaDAO.delete(id);
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
