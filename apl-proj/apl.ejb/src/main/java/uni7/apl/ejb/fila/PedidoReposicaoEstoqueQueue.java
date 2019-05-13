
package uni7.apl.ejb.fila;


import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;


@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/PedidoReposicaoEstoqueQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/PedidoReposicaoEstoqueQueue")})
public class PedidoReposicaoEstoqueQueue implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(PedidoReposicaoEstoqueQueue.class.toString());
    
    @Inject
    private JMSContext context;
    
    @Resource(lookup = "java:jboss/exported/jms/queue/ReposicaoEstoqueQueue")
    private Queue queue;

    @Override
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Pedido de reposição recebido: " + msg.getText());
            	
                context.createProducer().send(queue, msg.getText());
                LOGGER.info("Enviado pedido para fila de reposição");             
                
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