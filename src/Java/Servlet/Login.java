package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connection.DBconnet;
import Model.TaiKhoan;
import javax.servlet.ServletContext;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ServletContext context = request.getServletContext();
		context.setAttribute("tk", null);
		Cookie myCookie = new Cookie("user","logout");
		response.addCookie(myCookie);
		response.sendRedirect("home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBconnet DBcnn = new DBconnet();
		ServletContext context = request.getServletContext();
		context.setAttribute("tk", null);
		String nameTable = "account";
		List<TaiKhoan> listTK = new ArrayList<>();
		listTK = DBcnn.select(nameTable);
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		for (TaiKhoan tk : listTK) {
			if (tk.getUser().equals(user)) {
				if (tk.getPass().equals(pass)) {
					Cookie myCookie = new Cookie("user",tk.getUser());
					response.addCookie(myCookie);
					context.setAttribute("tk", tk);
				}
			}
		}
		if (context.getAttribute("tk") == null) {
			Cookie myCookie = new Cookie("logsig", "1");
			response.addCookie(myCookie);
		}
		response.sendRedirect("home");
	}

}
