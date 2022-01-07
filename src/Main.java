import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Main() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int search = 20;
		Boolean b1 = request.getParameter("searchNum") != null;
		if(b1 == true) {
			search = Integer.parseInt(request.getParameter("searchNum"));
		}
		Boolean b2 = request.getParameter("keyword") == null;
		// search.jsp
		if (b2 == true) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		// googleitem.jsp
		System.out.println("Searching: " + request.getParameter("keyword"));
		System.out.println("-".repeat(40));
		GoogleQuery q1 = new GoogleQuery(request.getParameter("keyword"), search);
		System.out.println(q1.show());
		
		String[][] arr = q1.ArrQuery();
		request.setAttribute("query", arr);
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}