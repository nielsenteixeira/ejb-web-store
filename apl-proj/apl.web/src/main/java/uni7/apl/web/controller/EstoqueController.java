package uni7.apl.web.controller;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uni7.apl.ejb.estocagem.Estoque;
import uni7.apl.ejb.estocagem.Produto;

@SessionScoped
public class EstoqueController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Estoque estoque;
	
	public List<Produto> getEstoque() {
		return estoque.getAll();
	}
	
	public Optional<Produto> getProdutoByCodigo(String codigo) {
		return estoque.getAll().stream().filter(p -> p.getCodigo().equals(codigo)).findFirst();
	}
}