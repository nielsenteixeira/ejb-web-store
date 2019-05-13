package uni7.apl.web.controller;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import uni7.apl.ejb.compra.Carrinho;
import uni7.apl.ejb.estocagem.Produto;
import uni7.apl.web.exception.ReposicaoEstoqueException;

@SessionScoped
public class CarrinhoController implements Serializable {
	private static final long serialVersionUID = 2145255640296403481L;

	@EJB
	Carrinho carrinho;
	
	@Inject EstoqueController estoqueController;
	
	public Carrinho finalizarCompra() throws ReposicaoEstoqueException {
		for(Produto produto: carrinho.listarProdutos()) {
			
			long quantidadeEmEstoque = estoqueController.getEstoque()
					.stream()
					.filter(produtoEmEstoque -> produto.getCodigo().equals(produtoEmEstoque.getCodigo()))
					.count();
			
			long quantidadeNoCarrinho = carrinho.listarProdutos()
					.stream()
					.filter(produtoNoCarrinho -> produto.getCodigo().equals(produtoNoCarrinho.getCodigo()))
					.count();
			
			if((quantidadeEmEstoque <= quantidadeNoCarrinho) || quantidadeEmEstoque < 2) {
				try {
					long quantidadeReposicao = quantidadeNoCarrinho + 2;
					estoqueController.efetuaPedidoReposicao(produto.getCodigo(), quantidadeReposicao);
				}catch (JsonProcessingException e) {
					throw new ReposicaoEstoqueException("Falha ao efetuar pedido de reposição de estoque!");
				}
				
			}
		}
				
		return this.carrinho.finalizarCompra();
	}
	public void esvaziarCarrinho() {
		this.carrinho.esvaziarCarrinho();
	}
	public List<Produto> removerItem(Produto item) {
		return this.carrinho.removerItem(item);
	}
	public List<Produto> adicionarItem(Produto item) throws InvalidObjectException {
		return this.carrinho.adicionarItem(item);
	}
	public List<Produto> listarProdutos() {
		return this.carrinho.listarProdutos();
	}
}
