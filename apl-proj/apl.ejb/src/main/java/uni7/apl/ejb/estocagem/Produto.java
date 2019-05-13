package uni7.apl.ejb.estocagem;
import java.io.Serializable;

public class Produto implements Serializable {
	private static final long serialVersionUID = -4287084788057725690L;
	private int quantidade;
	private String codigo;
	private String nome;
	private String descricao;
	private double preco;
	
	public Produto(String codigo) {
		this.codigo = codigo;
	}
	
	public Produto(int quantidade, String codigo, String nome, String descricao, double preco) {
		this.quantidade = quantidade;
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Produto (Produto produto) {
		this.quantidade = produto.quantidade;
		this.codigo = produto.codigo;
		this.nome = produto.nome;
		this.descricao = produto.descricao;
		this.preco = produto.preco;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}