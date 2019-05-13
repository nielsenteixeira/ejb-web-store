package uni7.apl.ejb.estocagem;

import java.io.Serializable;
import java.util.UUID;

public class PedidoReposicao implements Serializable {
	private static final long serialVersionUID = 5687634843575714536L;
	
	private UUID codigo;
	private String codigoProduto;
	private long quantidade;
	
	public PedidoReposicao(String codigoProduto, long quantidade) {
		super();
		this.codigo = UUID.randomUUID();
		this.codigoProduto = codigoProduto;
		this.quantidade = quantidade;
	}
	public UUID getCodigo() {
		return codigo;
	}
	
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
}
