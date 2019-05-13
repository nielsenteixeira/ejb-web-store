package uni7.apl.web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import com.fasterxml.jackson.core.JsonProcessingException;

import uni7.apl.ejb.estocagem.Estoque;
import uni7.apl.ejb.estocagem.Produto;

@SessionScoped
public class EstoqueController implements Serializable {
	private static final long serialVersionUID = -7753134804581675450L;
	@EJB
	Estoque estoque;

	public List<Produto> getEstoque() {
		return estoque.getAll();
	}
	
	public List<Produto> removeProdutoDoEstoque(Produto produto) {
		Optional<Produto> produtoRemover = estoque.getAll().stream().filter(p-> produto.getCodigo().equals(p.getCodigo())).findFirst();
		
		if(produtoRemover.isPresent()) {
			this.estoque.getAll().remove(produtoRemover.get());
		}
		
		return this.getEstoque();
	}


	public Optional<Produto> getProdutoByCodigo(String codigo) {
		return estoque.getAll().stream().filter(p -> p.getCodigo().equals(codigo)).findFirst();
	}
	
	public void efetuaPedidoReposicao(String codigoProduto, long quantidade) throws JsonProcessingException {
		estoque.efetuaPedidoReposicao(codigoProduto, quantidade);
	}
	
}