package easyway.activemq.app.demo2;

import javax.jms.JMSException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 测试类
 * @author longgangbai
 *
 */
public class MessageTest {
	public void test() throws JMSException {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("app-activemq-tomcat.xml");
		MessageSender sender=(MessageSender)ctx.getBean("sender");
		MessageReceiver receive=(MessageReceiver)ctx.getBean("receiver");
		sender.send("helloworld");
		receive.receive();
	}

}
