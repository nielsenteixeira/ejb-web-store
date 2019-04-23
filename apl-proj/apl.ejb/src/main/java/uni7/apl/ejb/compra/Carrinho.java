package uni7.apl.ejb.compra;

import java.io.InvalidObjectException;
import java.util.List;

import uni7.apl.ejb.estocagem.Produto;

public interface Carrinho {
	Carrinho finalizarCompra();
	void esvaziarCarrinho();
	List<Produto> removerItem(Produto item);
	List<Produto> adicionarItem(Produto item) throws InvalidObjectException;
	List<Produto> listarProdutos();
	double getPrecoCompra();
}
