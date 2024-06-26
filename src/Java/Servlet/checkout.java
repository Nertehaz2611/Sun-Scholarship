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
import Model.Cart;
import Model.SanPham;
import Model.SpOut;

/**
 * Servlet implementation class checkout
 */
@WebServlet("/checkout")
public class checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checkout() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBconnet DBcnn = new DBconnet();
		ServletContext context = request.getServletContext();
		Object tk = context.getAttribute("tk");
		if (context.getAttribute("tk") == null) {
			Cookie myCookie = new Cookie("logsig", "1");
			response.addCookie(myCookie);
			response.sendRedirect("home");
		} else {

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					String name = cookies[i].getName();
					if (name.equals("LogCr")) {
						if (cookies[i].getValue().equals("1")) {
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
			List<Cart> listOut = new ArrayList<>();
			listOut = DBcnn.selectCart("cart");
			List<SpOut> listSpOut = new ArrayList<>();
			int l = 1;
			int Subtotal = 0;
			for (Cart cr : listOut) {
				if (cr.getUser().equals(tk.toString())) {
					SanPham sp = DBcnn.selectSp("SanPham", cr.getMasp());
					int slKho = DBcnn.selectCart(tk.toString(), cr.getMasp());
					SpOut Sp = new SpOut(l, sp.getMaSp(), sp.getTenSp(), sp.getGia(), slKho);
					listSpOut.add(Sp);
					Subtotal = Subtotal + Sp.getSum();
					l = l + 1;
				}
			}
			int Total = Subtotal * 90 / 100;
			int slCart = 0;
			if (tk != null) {
				slCart = DBcnn.selectSlCart("cart", tk.toString());
			}
			context.setAttribute("slCart", slCart);
			request.setAttribute("Subtotal", Subtotal);
			request.setAttribute("Total", Total);
			request.setAttribute("listSpCheckOut", listSpOut);
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
