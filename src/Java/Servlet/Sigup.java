package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connection.DBconnet;
import Model.TaiKhoan;

/**
 * Servlet implementation class Sigup
 */
@WebServlet("/sigup")
public class Sigup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sigup() {
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
		// TODO Auto-generated method stub
		DBconnet DBcnn = new DBconnet();
		String user = request.getParameter("user");
		String pass = request.getParameter("psw");
		String pass2 = request.getParameter("psw-repeat");
		String email = request.getParameter("email");
		String nameTable = "account";
		List<TaiKhoan> listTK = new ArrayList<>();
		listTK = DBcnn.select(nameTable);
		String loi = "";
		ServletContext context = request.getServletContext();
		context.setAttribute("loi", loi);
		Cookie myCookie;
		boolean TF = true;
		if (pass.equals(pass2) == false) {
			TF = false;
			loi = "*The re-entered password does not match";
			myCookie = new Cookie("logsig", "2");
			response.addCookie(myCookie);
		}
		for (TaiKhoan i : listTK) {
			if (i.getUser().equals(user)) {
				TF = false;
				loi = "*Account already exists";
				myCookie = new Cookie("logsig", "2");
				response.addCookie(myCookie);
			}
		}
		if (TF) {
			TaiKhoan tk = new TaiKhoan(user, pass, email);
			DBcnn.insertAcc(new Object[] {tk.getUser(), tk.getPass(), tk.getEmail()});
		}
		context.setAttribute("loi", loi);
		response.sendRedirect("home");
	}

}
