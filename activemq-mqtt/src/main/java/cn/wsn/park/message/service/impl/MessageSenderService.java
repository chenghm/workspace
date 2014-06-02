package cn.wsn.park.message.service.impl;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import cn.wsn.park.message.service.IMessageSenderService;

@Service
public class MessageSenderService implements IMessageSenderService {

	@Autowired
	private JmsTemplate inspectorJmsTemplate;

	@Override
	public void sendMessage(final String message, final String clientId) {

		inspectorJmsTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("text", message);
				mapMessage.setStringProperty("clientId", clientId);

				return mapMessage;
			}
		});
	}

}
