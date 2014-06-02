package easyway.activemq.app.demo2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
/**
 * 消息接收者
 * @author longgangbai
 *
 */
public class MessageReceiver implements  MessageListener {
	private JmsTemplate jmsTemplate;
	  
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
    public void receive() throws JMSException{
    	 TextMessage text=(TextMessage)this.jmsTemplate.receive();
		System.out.println("receive="+text.getText());
    }
	public void onMessage(Message message) {
		 if(message instanceof TextMessage){
			 TextMessage text=(TextMessage)message;
			 try {
				System.out.println(text.getText());
			} catch (Exception e) {
			}
		 }
	}
    
}
