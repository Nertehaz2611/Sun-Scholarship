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

import com.oreilly.servlet.MultipartRequest;

import Connection.DBconnet;
import Model.DanhMuc;

/**
 * Servlet implementation class dangbai
 */
@WebServlet("/dangbai")
public class dangbai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangbai() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String name = "danhmuc";
		List<DanhMuc> listOut = new ArrayList<>();
		listOut = DBcnn.selectDm(name);
		int slCart = 0;
		if (tk != null) {
			slCart = DBcnn.selectSlCart("cart", tk.toString());
		}
		context.setAttribute("slCart", slCart);
		request.setAttribute("listDM", listOut);
		request.getRequestDispatcher("dangbai.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ServletContext context = request.getServletContext();
			String realpath = context.getRealPath("/img/shop");
			System.out.println(realpath);
			MultipartRequest multi = new MultipartRequest(request, realpath);
			String masp = multi.getParameter("masp");
			String tensp = multi.getParameter("tensp");
			String giasp = multi.getParameter("giasp");
			String soluong = multi.getParameter("soluong");
			String danhmuc = multi.getParameter("danhmuc");
			String mota = multi.getParameter("mota");
			String fileName = multi.getFilesystemName("file");
			
			boolean TF = true;
			int max = -28;
			DBconnet DBcnn = new DBconnet();
			List<String> listCheck = new ArrayList<>();
			listCheck = DBcnn.selectMasp();
			for (String s : listCheck) {
				if (s.equals(masp)) {
					TF = false;
					break;
				}
			}
			if (TF == false) {
				int kk = -28;
				for (String s : listCheck) {
					kk = Integer.parseInt(s.substring(2, s.length()));
					System.out.println(kk);
					if (kk > max) {
						max = kk;
					}
				}
				max = max + 1;
				if (max < 100 && max > 9) {
					masp = "SP0" + String.valueOf(max);
				}
				else {
					if (max < 10) {
						masp = "SP00" + String.valueOf(max);
					}
					else {
						masp = "SP" + String.valueOf(max);
					}
				}
			}
			Cookie myCookie;
			int trg = listCheck.size() / 9 + 1;
			myCookie = new Cookie("k", String.valueOf(trg));
			response.addCookie(myCookie);
			System.out.println(masp);
			DBcnn.insertSp(new Object[] {masp, tensp, mota, giasp, fileName, danhmuc});
			DBcnn.insertKho(new Object[] {masp, soluong});
			System.out.println("Dang Bai Thanh Cong");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Dang Bai That Bai");
		}
		response.sendRedirect("shop");
	}

}
