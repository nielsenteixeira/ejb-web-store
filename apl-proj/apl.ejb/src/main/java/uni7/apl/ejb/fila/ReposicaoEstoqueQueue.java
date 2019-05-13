
package uni7.apl.ejb.fila;

import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import uni7.apl.ejb.estocagem.Estoque;
import uni7.apl.ejb.estocagem.PedidoReposicao;
import uni7.apl.ejb.estocagem.Produto;


@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/ReposicaoEstoqueQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/ReposicaoEstoqueQueue")})
public class ReposicaoEstoqueQueue implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(ReposicaoEstoqueQueue.class.toString());
    
    @EJB
    Estoque estoque;

    @Override
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Received Message from queue: " + msg.getText());
                
                PedidoReposicao pedido = new Gson().fromJson(msg.getText(), PedidoReposicao.class);
                
                Optional<Produto> produto = estoque.getAll()
                		.stream()
                		.filter(p -> pedido.getCodigoProduto().equals(p.getCodigo()))
                		.findFirst();
                
                if(produto.isPresent()) {
                	for(int i = 0; i < pedido.getQuantidade(); i++) {
                    	estoque.adicionaProduto(new Produto(produto.get()));
                    }
                	LOGGER.info("Produtos adicionados ao estoque: " + msg.getText());
                }                
                
            } else {
                LOGGER.warning("Conteúdo da mensagem incorreto: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
    
    @PostConstruct  
    public void myInit () {  
    	LOGGER.info("MDB Init");
    } 
}