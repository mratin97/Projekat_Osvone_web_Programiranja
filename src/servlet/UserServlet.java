package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.Korisnik;
import dao.FilmDAO;
import dao.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
	
			Map<String, Object> data = new LinkedHashMap<>();
	
			String action = request.getParameter("action");
			switch (action) {
				case "loggedInUserRole": {
					data.put("loggedInUserRole", loggedInUser.getRole());
					break;
				}
			}
			
			data.put("loggedInUserRole", loggedInUser.getRole());
			data.put("loggedInUserName", loggedInUser.getId());
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("nameFilter");
			name = (name != null? name: "");
			List<Korisnik> korisnici = UserDAO.getAll(name);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("korisnici", korisnici);

			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String action = request.getParameter("action");
		
			if(action=="update") {
			
				String id = request.getParameter("id");
				Korisnik user;
				try {
					user = UserDAO.get(id);
					String pass=request.getParameter("pass");
					String role=request.getParameter("role");
					
					user.setId(id);
					user.setPass(pass);
					UserDAO.update(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
				
				
			
		


}}
