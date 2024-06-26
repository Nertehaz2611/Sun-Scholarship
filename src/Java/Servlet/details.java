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
import Model.SanPham;

/**
 * Servlet implementation class details
 */
@WebServlet("/details")
public class details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String MaSp = request.getParameter("masp");
		String nameTable = "SanPham";
		SanPham Sp;
		DBconnet DBcnn = new DBconnet();
		ServletContext context = request.getServletContext();
		Object tk = context.getAttribute("tk");
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName();
				if (name.equals("LogCr")) {
					if (cookies[i].getValue().equals("1"))
					{
						String NameUser = "";
						if (tk != null) {
							NameUser = tk.toString();
						}
						Cookie cookiee = new Cookie("LogCr", "-1");
						response.addCookie(cookiee);
						String mau = "cartSP022";
						String mau1 = "addcartSP022";
						for (int t = 0; t < cookies.length; t++) {
							if (cookies[t].getName().length() >= mau.length()) {
								if (cookies[t].getName().substring(0,3).equals("add")) {
									String nameCookie = cookies[t].getName().substring(7, mau1.length());
									int oldVal = DBcnn.selectCart(NameUser, nameCookie);
									int newVal = Integer.parseInt(cookies[t].getValue());
									int maxVal = DBcnn.selectKho("kho", nameCookie);
									if (oldVal != newVal) {
										if (newVal > maxVal) {
											newVal = maxVal;
										}
										DBcnn.updateCart(new Object[] {newVal, NameUser, nameCookie});
									}
									Cookie cookieToDelete = new Cookie(cookies[t].getName(), "");
									cookieToDelete.setMaxAge(0);
									response.addCookie(cookieToDelete);
								}
								if (cookies[t].getName().substring(0,4).equals("cart")) {
									String nameCookie = cookies[t].getName().substring(4, mau.length());
									int oldVal = DBcnn.selectCart(NameUser, nameCookie);
									int newVal = Integer.parseInt(cookies[t].getValue());
									int maxVal = DBcnn.selectKho("kho", nameCookie);
									if (oldVal != 0) {
										newVal = newVal + oldVal;
										if (newVal > maxVal) {
											newVal = maxVal;
										}
										DBcnn.updateCart(new Object[] {newVal, NameUser, nameCookie});
									}
									else {
										if (newVal > maxVal) {
											newVal = maxVal;
										}
										DBcnn.insertCart(new Object[] {NameUser, nameCookie, newVal});
									}
									Cookie cookieToDelete = new Cookie(cookies[t].getName(), "");
									cookieToDelete.setMaxAge(0);
									response.addCookie(cookieToDelete);
								}
							}
						}
					}
				}
			}
		}
		Sp = DBcnn.select(nameTable, MaSp);
		int slCart = 0;
		if (tk != null) {
			slCart = DBcnn.selectSlCart("cart", tk.toString());
		}
		context.setAttribute("slCart", slCart);
		request.setAttribute("Sp", Sp);
		request.getRequestDispatcher("details.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
