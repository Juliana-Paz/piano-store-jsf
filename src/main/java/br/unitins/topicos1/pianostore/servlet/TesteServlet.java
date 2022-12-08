package br.unitins.topicos1.pianostore.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/teste")
public class TesteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2080369629786478126L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println("<html>");
		response.getWriter().println("<body>");
		response.getWriter().println("<h1>EXEMPLO AAAA</h1>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");

	}
}
