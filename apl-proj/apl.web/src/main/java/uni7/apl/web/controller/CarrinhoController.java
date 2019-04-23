package uni7.apl.web.controller;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import uni7.apl.ejb.compra.Carrinho;
import uni7.apl.ejb.estocagem.Produto;

@SessionScoped
public class CarrinhoController implements Serializable {
	private static final long serialVersionUID = 2145255640296403481L;

	@EJB
	Carrinho carrinho;
	
	public Carrinho finalizarCompra() {
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
