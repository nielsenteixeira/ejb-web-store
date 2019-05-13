package uni7.apl.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

import com.google.gson.Gson;

import uni7.apl.ejb.compra.Carrinho;
import uni7.apl.ejb.estocagem.Produto;
import uni7.apl.web.controller.CarrinhoController;
import uni7.apl.web.controller.EstoqueController;
import uni7.apl.web.exception.ReposicaoEstoqueException;
import uni7.apl.web.util.Acao;
import uni7.apl.web.util.FormatoResposta;
import uni7.apl.web.util.HtmlBuilder;
import uni7.apl.web.util.RequestOptions;

@WebServlet("/CarrinhoServlet")
public class CarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = -3207027809241459928L;
	
	@Inject CarrinhoController carrinhoController;
	@Inject EstoqueController estoqueController;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestOptions requestOptions = new RequestOptions();
		
		Acao acao = requestOptions.getAcao(request, response);
		FormatoResposta formatoResposta = requestOptions.getFormatoResposta(request, response);
		
		PrintWriter out = response.getWriter();
		HtmlBuilder htmlBuilder = new HtmlBuilder();
		
		String codigoProduto;
		
		switch (acao) {
			case LISTAR:
				List<Produto> produtos = carrinhoController.listarProdutos();
				
				if(formatoResposta == FormatoResposta.JSON) {
					String produtosJson = formatToJSON(produtos, response);
			        out.print(produtosJson);
			        
				} else {
					String htmlProdutos = htmlBuilder.buildHtmlList("Carrinho", produtos);
					out.print(htmlProdutos);
				}
			
				break;
			
			case ADICIONAR:
				codigoProduto = request.getParameter("produto");
				
				if(codigoProduto == null) {
					response.sendError(HttpStatus.SC_UNPROCESSABLE_ENTITY, "Código de produto inválido!");
				} else {
					Optional<Produto> produto = estoqueController.getProdutoByCodigo(codigoProduto);
					
					if(produto.isPresent()) {
						List<Produto> produtosNoCarrinho = carrinhoController.adicionarItem(produto.get());
						
						if(formatoResposta == FormatoResposta.JSON) {
							String produtosJson = formatToJSON(produtosNoCarrinho, response);
					        out.print(produtosJson);
					        
						} else {
							String htmlProdutos = htmlBuilder.buildHtmlList("Carrinho", produtosNoCarrinho);
							out.print(htmlProdutos);
						}						
						
					} else {
						response.sendError(HttpStatus.SC_UNPROCESSABLE_ENTITY, "Produto não existe no estoque!");
					}
				}
				break;
			case REMOVER:
				codigoProduto = request.getParameter("produto");
				
				if(codigoProduto == null) {
					response.sendError(HttpStatus.SC_BAD_REQUEST, "Código de produto inválido!");
				} else {
					Produto produto = new Produto(codigoProduto);
					List<Produto> produtosNoCarrinho = carrinhoController.removerItem(produto);
					
					if(formatoResposta == FormatoResposta.JSON) {
						String produtosJson = formatToJSON(produtosNoCarrinho, response);
				        out.print(produtosJson);
				        
					} else {
						String htmlProdutos = htmlBuilder.buildHtmlList("Carrinho", produtosNoCarrinho);
						out.print(htmlProdutos);
					}
				}
				break;
				
			
			case ESVAZIAR:
					carrinhoController.esvaziarCarrinho();
					out.print("<html><body><h3>Carrinho esvaziado!</h3></body</html>");
				break;
				
			case FINALIZAR:
					try {
						Carrinho carrinho = carrinhoController.finalizarCompra();
						String htmlCompra = htmlBuilder.buildHtmlCarrinho(carrinho);
						carrinho.esvaziarCarrinho();
						out.print(htmlCompra);
					} catch (ReposicaoEstoqueException e) {
						out.print("Falha ao tentar finalizar compra. Por favor tente novamente!");
					}
					
				break;
			
			}
		
		out.flush();
	}
	
	private String formatToJSON(List<Produto> produtos, HttpServletResponse response) {
		String estoqueStr = new Gson().toJson(produtos);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return estoqueStr;
	}
}
