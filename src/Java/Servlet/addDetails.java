package Servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connection.DBconnet;

/**
 * Servlet implementation class addDetails
 */
@WebServlet("/addsp")
public class addDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String MaSp = request.getParameter("maspadd");
		DBconnet DBcnn = new DBconnet();
		ServletContext context = request.getServletContext();
		Object tk = context.getAttribute("tk");
		if (tk != null) {
			String NameUser = tk.toString();
			int sl = DBcnn.selectCart(NameUser, MaSp);
			if (sl < 0) {
				DBcnn.insertCart(new Object[] {NameUser, MaSp, 1});
			}
			else {
				DBcnn.updateCart(new Object[] {sl + 1, NameUser, MaSp});
			}
			int slCart = 0;
			if (tk != null) {
				slCart = DBcnn.selectSlCart("cart", tk.toString());
			}
			context.setAttribute("slCart", slCart);
			response.sendRedirect("shop");
		}
		else {
			Cookie myCookie = new Cookie("logsig", "1");
			response.addCookie(myCookie);
			response.sendRedirect("home");
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
