package uni7.apl.ejb.estocagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Session Bean implementation class EstoqueBean
 */
@Startup
@Singleton
public class EstoqueBean implements Estoque, Serializable {
	
	private static final long serialVersionUID = 1343630805296128500L;
	
	private static final Logger LOGGER = Logger.getLogger(EstoqueBean.class.toString());

	@Inject
    private JMSContext context;
    
    @Resource(lookup = "java:jboss/exported/jms/queue/PedidoReposicaoEstoqueQueue")
    private Queue queue;

	private List<Produto> produtos;

	public EstoqueBean() {
		produtos = new ArrayList<Produto>();
	}

	@PostConstruct
	public void init() {
		Produto produto1 = new Produto(5, "d78d8653", "Geladeira Brastemp", "Geladeira Brastemp 450L", 2500D);
		Produto produto2 = new Produto(2, "9b456dcc", "Freezer", "Freezer 300L", 1600D);
		Produto produto3 = new Produto(3, "9599fafa", "TV 50'", "TV 50' 4K", 2400D);
		Produto produto4 = new Produto(1, "73781f22", "TV 42'", "TV 42' Full HD", 1980D);
		Produto produto5 = new Produto(1, "6d47d2ff", "Microondas", "Microondas 30L", 600D);
		Produto produto6 = new Produto(1, "c55896e2", "Sanduicheira", "Sanduicheira dupla multiuso", 80D);
		Produto produto7 = new Produto(1, "6a35a4d0", "Máquina de Café", "Máquina de Café Nespresso", 350D);
		Produto produto8 = new Produto(1, "97e2282c", "Máquina de Lavar", "Máquina de Lavar 12Kg", 1400D);
		Produto produto9 = new Produto(1, "e4b19d8b", "Ar condicionado", "Ar condicionado 9000 BTUs", 1200D);
		Produto produto10 = new Produto(1, "a695afbf", "Air fryer", "Air fryer Philips Walita", 10D);
		
		produtos.add(produto1);
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

	@Override
	public int getQuantidadeMinimaPorProduto() {
		return 1;
	}

	@Override
	public PedidoReposicao efetuaPedidoReposicao(String codigoProduto, long quantidade) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
    	PedidoReposicao pedido = new PedidoReposicao(codigoProduto, quantidade);
    	String mensagem = objectMapper.writeValueAsString(pedido);
    	
        context.createProducer().send(queue, mensagem);
        LOGGER.info("Pedido enviado: " + mensagem);
        
        return pedido;
	}

	@Override
	public List<Produto> adicionaProduto(Produto prod) {		
		for (Produto produto : this.produtos) {
			if(prod.getCodigo().equals(produto.getCodigo())) {
				produto.setQuantidade(produto.getQuantidade() + 1);
			}
		}		
		return this.produtos;
	}
}