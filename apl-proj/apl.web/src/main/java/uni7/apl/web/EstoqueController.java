package uni7.apl.web;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import uni7.apl.ejb.EstoqueBean;
import uni7.apl.ejb.Produto;

@SessionScoped
public class EstoqueController {
	@EJB
	EstoqueBean estoque;
	
	public List<Produto> getEstoque() {
		return estoque.getAll();
	}
}