package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Redirect;

import dao.FilmDAO;
import dao.KartaDAO;
import dao.ProjekcijaDAO;
import dao.UserDAO;
import model.Film;
import model.Karta;
import model.Korisnik;
import model.Projekcija;

/**
 * Servlet implementation class KartaServlet
 */
public class KartaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KartaServlet() {
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
			List<Projekcija> filmovi = ProjekcijaDAO.getAllbyId(name);
			Map<String, Object> data = new LinkedHashMap<>();
			Korisnik loggedInUser = UserDAO.get(loggedInUserName);
			data.put("loggedInUserRole", loggedInUser.getRole());
			data.put("loggedInUserName", loggedInUser.getId());
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
		// TODO Auto-generated method stub
		String id= request.getParameter("id");
		String datum = request.getParameter("datum");
		String projekcija = request.getParameter("projekcija");
		String BrKarata=request.getParameter("BrKarata");
		String sediste=request.getParameter("sediste");
		int zauzeto=0;
		int sedisteint=Integer.parseInt(sediste);
		int broj=Integer.parseInt(BrKarata);
		String korisnik=(String) request.getSession().getAttribute("loggedInUserName");
		try {
			List<Karta> karte =KartaDAO.getAll(projekcija);
			
			for(Karta karta1 : karte){
				switch (BrKarata) {
				case "2":
					if(sedisteint==Integer.parseInt(karta1.getSediste()) || (sedisteint==Integer.parseInt(karta1.getSediste())) ) {
						 zauzeto=1;
					}
					break;
				case "3":
					if(sedisteint==Integer.parseInt(karta1.getSediste()) || (sedisteint==Integer.parseInt(karta1.getSediste()) || sedisteint==Integer.parseInt(karta1.getSediste()) )) {
						 zauzeto=1;
					}
					break;
				
				default:
					if(Integer.parseInt(sediste)==Integer.parseInt(karta1.getSediste())) {
						 zauzeto=1;
					}
					break;
				}
				
				
			}
			
			if (zauzeto==0) {
				int id1=Integer.parseInt(id);
			try {
				for(int i=0;i<broj;i++) {
				sedisteint+=i;
				sediste=String.valueOf(sedisteint);
				
				id1++;
				String idupis=String.valueOf(id1);
				Karta karta=new Karta(idupis, projekcija, sediste, datum, korisnik);
				KartaDAO.add(karta);
				}
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			else {
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
				//response.sendRedirect("Error.html");
				return;
			}
			} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
