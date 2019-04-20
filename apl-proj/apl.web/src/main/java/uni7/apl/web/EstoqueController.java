package uni7.apl.web;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import uni7.apl.ejb.Estoque;
import uni7.apl.ejb.Produto;

@SessionScoped
public class EstoqueController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Estoque estoque;
	
	public List<Produto> getEstoque() {
		return estoque.getAll();
	}
}