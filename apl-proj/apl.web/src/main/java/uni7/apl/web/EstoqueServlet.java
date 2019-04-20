package uni7.apl.web;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/EstoqueServlet")
public class EstoqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject EstoqueController controller;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var acao = Acao.valueOf(request.getParameter("acao").toUpperCase());
		
		switch (acao) {
		case LISTAR:
			var estoque = new Gson().toJson(controller.getEstoque());
			
			var out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(estoque);
	        out.flush();   
			break;

		default:
			break;
		}
	}
}