package uni7.apl.ejb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Session Bean implementation class EstoqueBean
 */
@Startup
@Singleton
public class EstoqueBean implements Estoque, Serializable {
	
	private static final long serialVersionUID = 1343630805296128500L;

	private List<Produto> produtos;

	public EstoqueBean() {
		produtos = new ArrayList<Produto>();
	}

	@PostConstruct
	public void init() {
		var produto1a = new Produto(1, "d78d8653", "Geladeira Brastemp", "Geladeira Brastemp 450L", 2500D);
		var produto1b = new Produto(2, "d78d8653", "Geladeira Brastemp", "Geladeira Brastemp 450L", 2500D);
		var produto1c = new Produto(3, "d78d8653", "Geladeira Brastemp", "Geladeira Brastemp 450L", 2500D);
		var produto1d = new Produto(4, "d78d8653", "Geladeira Brastemp", "Geladeira Brastemp 450L", 2500D);
		var produto1e = new Produto(5, "d78d8653", "Geladeira Brastemp", "Geladeira Brastemp 450L", 2500D);
		
		var produto2 = new Produto(6, "9b456dcc", "Freezer", "Freezer 300L", 1600D);
		var produto3 = new Produto(7, "9599fafa", "TV 50'", "TV 50' 4K", 2400D);
		var produto4 = new Produto(8, "73781f22", "TV 42'", "TV 42' Full HD", 1980D);
		var produto5 = new Produto(9, "6d47d2ff", "Microondas", "Microondas 30L", 600D);
		var produto6 = new Produto(10, "c55896e2", "Sanduicheira", "Sanduicheira dupla multiuso", 80D);
		var produto7 = new Produto(11, "6a35a4d0", "Máquina de Café", "Máquina de Café Nespresso", 350D);
		var produto8 = new Produto(12, "97e2282c", "Máquina de Lavar", "Máquina de Lavar 12Kg", 1400D);
		var produto9 = new Produto(13, "e4b19d8b", "Ar condicionado", "Ar condicionado 9000 BTUs", 1200D);
		var produto10 = new Produto(14, "a695afbf", "Air fryer", "Air fryer Philips Walita", 10D);
		
		produtos.add(produto1a);
		produtos.add(produto1b);
		produtos.add(produto1c);
		produtos.add(produto1d);
		produtos.add(produto1e);
		
		produtos.add(produto2);
		produtos.add(produto3);
		produtos.add(produto4);
		produtos.add(produto5);
		produtos.add(produto6);
		produtos.add(produto7);
		produtos.add(produto8);
		produtos.add(produto9);
		produtos.add(produto10);
	}

	@Override
	public List<Produto> getAll() {
		return produtos;
	}

}