package uni7.apl.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

import com.google.gson.Gson;

import uni7.apl.ejb.Produto;

@WebServlet("/EstoqueServlet")
public class EstoqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject EstoqueController controller;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		Acao acao = getAcao(request, response);
		FormatoResposta formatoResposta = getFormatoResposta(request, response);
		
		PrintWriter out = response.getWriter();
		
		switch (acao) {
		case LISTAR:
			List<Produto> produtos = controller.getEstoque();
			
			
			if(formatoResposta == FormatoResposta.JSON) {
				String estoqueStr = new Gson().toJson(produtos);
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        out.print(estoqueStr);
		        
			} else {
				HtmlBuilder htmlBuilder = new HtmlBuilder();
				String htmlProdutos = htmlBuilder.buildHtmlList(produtos);
				out.print(htmlProdutos);
			}
			
			break;
		}
		
		out.flush();
	}

	private FormatoResposta getFormatoResposta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("formato") != null) {
			try	{
				return FormatoResposta.valueOf(request.getParameter("formato").toUpperCase());
			} catch (IllegalArgumentException e) {
				response.sendError(HttpStatus.SC_BAD_REQUEST, "Escolha um formato válido: 'json' ou 'html'.");
			}
		} 
		
		return FormatoResposta.HTML;
	}
	
	private Acao getAcao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("acao") != null) {
			try {
				return Acao.valueOf(request.getParameter("acao").toUpperCase());
			} catch (IllegalArgumentException e) {
				response.sendError(HttpStatus.SC_BAD_REQUEST, "Escolha uma ação válida: 'listar'.");
			}
		} 
		
		return Acao.LISTAR;
	}
}