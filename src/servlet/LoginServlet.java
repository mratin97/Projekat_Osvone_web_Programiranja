package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Korisnik;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		try {
			Korisnik user = UserDAO.get(userName, password);
			if (user == null) {
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
				return;
			}
	
			request.getSession().setAttribute("loggedInUserName", user.getId());
			
			
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
