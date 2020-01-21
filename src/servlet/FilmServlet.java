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
				String id = request.getParameter("id");
				Map<String, Object> data = new LinkedHashMap<>();
				try {
					Film film= FilmDAO.get(id);
					data.put("film", film);
					request.setAttribute("data", data);
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
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "add": {
				String naziv = request.getParameter("naziv");
				naziv = (!"".equals(naziv)? naziv: "<prazan naziv>");
				String reziser = request.getParameter("reziser");
				reziser = (!"".equals(reziser)? naziv: "<prazan reziser>");
				String glumci = request.getParameter("glumci");
				glumci = (!"".equals(glumci)? naziv: "<prazan glumci>");
				String zanr = request.getParameter("zanr");
				zanr = (!"".equals(zanr)? naziv: "<prazan zanr>");
				String distributer = request.getParameter("distributer");
				distributer = (!"".equals(distributer)? naziv: "<prazan distributer>");
				String zemlja = request.getParameter("zemlja");
				zemlja = (!"".equals(zemlja)? naziv: "<prazan zemlja>");
				String opis = request.getParameter("opis");
				opis = (!"".equals(reziser)? naziv: "<prazan opis>");
				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				trajanje = (trajanje > 0? trajanje: 3000);
				int godina = Integer.parseInt(request.getParameter("godina"));
				godina = (godina > 0? godina: 3000);

				Film film = new Film("123", naziv, reziser,glumci,zanr,trajanje,distributer,zemlja,godina,opis);
				
				try {
					FilmDAO.add(film);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				break;
			}
			
	}

	}}
