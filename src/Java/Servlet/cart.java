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
import Model.SanPhamCart;
import Model.SanPham;

/**
 * Servlet implementation class cart
 */
@WebServlet("/cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public cart() {
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
		String nameTable = "cart";
		ServletContext context = request.getServletContext();
		if (context.getAttribute("tk") == null) {
			Cookie myCookie = new Cookie("logsig", "1");
			response.addCookie(myCookie);
			response.sendRedirect("home");
		} else {
			String tk = context.getAttribute("tk").toString();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					String Name = cookies[i].getName().substring(0, cookies[i].getName().length());
					if (Name.length() > 7) {
						String nameCookie = cookies[i].getName().substring(4, cookies[i].getName().length());
						String nameCookie2 = cookies[i].getName().substring(0, 4);
						if (nameCookie2.equals("cart")) {
							int slThem = Integer.parseInt(cookies[i].getValue());
							int slHienTai = DBcnn.findCart(nameTable, tk, nameCookie);
							if (slHienTai > 0) {
								int slKho = DBcnn.selectKho("kho", nameCookie);
								slHienTai = slHienTai + slThem;
								if (slHienTai > slKho) {
									slHienTai = slKho;
								}
								String sl = String.valueOf(slHienTai);
//	                        	DBcnn.updateCart(tk, nameCookie, sl);         
								DBcnn.updateCart(new Object[] { sl, tk, nameCookie });
							} else {
								int slKho = DBcnn.selectKho("kho", nameCookie);
								slHienTai = slHienTai + slThem;
								if (slHienTai > slKho) {
									slHienTai = slKho;
								}
								if (slHienTai > 0) {
									String sl = String.valueOf(slHienTai);
//		                        	DBcnn.insertCart(tk, nameCookie, sl);
									DBcnn.insertCart(new Object[] { tk, nameCookie, sl });
								}
							}
							Cookie cookieToDelete = new Cookie(cookies[i].getName(), "");
							cookieToDelete.setMaxAge(0);
							response.addCookie(cookieToDelete);
							cookieToDelete = new Cookie("addcart" + nameCookie, "");
							cookieToDelete.setMaxAge(0);
							response.addCookie(cookieToDelete);
						}
					}

				}
			}

			List<Cart> listOut = new ArrayList<>();
			listOut = DBcnn.selectCart(nameTable);
			List<SanPhamCart> listSpCart = new ArrayList<>();
			for (Cart cr : listOut) {
				if (cr.getUser().equals(tk)) {
					SanPham sp = DBcnn.selectSp("SanPham", cr.getMasp());
					int slKho = DBcnn.selectKho("kho", cr.getMasp());
					SanPhamCart spc = new SanPhamCart(sp.getMaSp(), sp.getTenSp(), sp.getGia(), sp.getAnh(), cr.getSl(),
							slKho);
					listSpCart.add(spc);
				}
			}
			int slCart = 0;
			if (tk != null) {
				slCart = DBcnn.selectSlCart("cart", tk.toString());
			}
			context.setAttribute("slCart", slCart);
			request.setAttribute("listSpCart", listSpCart);
			request.getRequestDispatcher("cart.jsp").forward(request, response);

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
