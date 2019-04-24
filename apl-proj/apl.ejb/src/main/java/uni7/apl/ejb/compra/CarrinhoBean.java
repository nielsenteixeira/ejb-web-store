package uni7.apl.ejb.compra;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateful;

import uni7.apl.ejb.estocagem.Produto;

@Stateful
public class CarrinhoBean implements Carrinho, Serializable {
	private static final long serialVersionUID = -7990156041735403233L;
	
	private List<Produto> produtos;
	private double valorTotalCompra;
	private boolean compraFinalizada;

	public CarrinhoBean() {
		this.produtos = new ArrayList<Produto>();
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public boolean isCompraFinalizada() {
		return compraFinalizada;
	}
	
	public double getvalorTotalCompra() {
		return valorTotalCompra;
	}
	
	@Override
	public Carrinho finalizarCompra() {
		this.compraFinalizada = true;
		this.valorTotalCompra = getPrecoCompra();
		return this;
		
	}

	@Override
	public void esvaziarCarrinho() {
		this.produtos.clear();
		
	}

	@Override
	public List<Produto> removerItem(Produto item) {
		Optional<Produto> produtoToRemove = this.produtos
				.stream()
				.filter(p -> p.getCodigo().equals(item.getCodigo()))
				.findFirst();
		
		if(produtoToRemove.isPresent()) {
			this.produtos.remove(produtoToRemove.get());
		}
		
		return produtos;
	}

	@Override
	public List<Produto> adicionarItem(Produto item) throws InvalidObjectException {
		if(item.getCodigo() != null && item.getCodigo() != "" && item.getPreco() > 0) {
			this.produtos.add(item);
			return produtos;
		} else {
			throw new InvalidObjectException("Produto inválido!");
		}
	}

	@Override
	public List<Produto> listarProdutos() {
		return this.produtos;
	}

	@Override
	public double getPrecoCompra() {
		return this.produtos
				.stream()
				.mapToDouble(p -> p.getPreco()).sum();
	}

	
	
}
