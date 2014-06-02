package easyway.activemq.app.demo2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * tomcat和activemq整合
 * 消息发送者
 * @author longgangbai
 *
 */
public class MessageSender {
  private JmsTemplate jmsTemplate;
  
  
  public void send(final String text){
	  jmsTemplate.send(new MessageCreator(){

		public Message createMessage(Session session) throws JMSException {
			// TODO Auto-generated method stub
			return session.createTextMessage(text);
		}
	  });
  }


	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
