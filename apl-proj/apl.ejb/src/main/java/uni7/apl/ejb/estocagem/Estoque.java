package uni7.apl.ejb.estocagem;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Estoque {
	List<Produto> getAll();
	List<Produto> adicionaProduto(Produto produto);
	int getQuantidadeMinimaPorProduto();
	PedidoReposicao efetuaPedidoReposicao(String codigoProduto, long quantidade) throws JsonProcessingException;
}