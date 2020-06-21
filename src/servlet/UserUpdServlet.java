package servlet;

import java.io.IOException;
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
 * Servlet implementation class UserUpdServlet
 */
public class UserUpdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			
			case "update": {

				String id2 = request.getParameter("id2");
				Korisnik user;
				try {
					user = UserDAO.get(id2);
					String id=request.getParameter("id");
					String pass=request.getParameter("pass");
					String role=request.getParameter("role");
					
					user.setId(id);
					user.setPass(pass);
					UserDAO.update(user,id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "delete": {
				String id = request.getParameter("id");
				//FilmDAO.delete(id);
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
