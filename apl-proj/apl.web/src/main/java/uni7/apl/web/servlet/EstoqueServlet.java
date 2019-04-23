package uni7.apl.web.servlet;
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

import uni7.apl.ejb.estocagem.Produto;
import uni7.apl.web.controller.EstoqueController;
import uni7.apl.web.util.Acao;
import uni7.apl.web.util.FormatoResposta;
import uni7.apl.web.util.HtmlBuilder;
import uni7.apl.web.util.RequestOptions;

@WebServlet("/EstoqueServlet")
public class EstoqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject EstoqueController controller;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		RequestOptions requestOptions = new RequestOptions();
		
		Acao acao = requestOptions.getAcao(request, response);
		FormatoResposta formatoResposta = requestOptions.getFormatoResposta(request, response);
		
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
				String htmlProdutos = htmlBuilder.buildHtmlList("Estoque", produtos);
				out.print(htmlProdutos);
			}
			
			break;
		}
		
		out.flush();
	}
}