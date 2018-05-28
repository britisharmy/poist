package com.boilerplate.services;

import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;

@EnableRabbit
@Service
public class MessageListenerService implements MessageListener {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MessageListenerService.class);
	private static final String FIRST_QUEUE = "bottomlesspit";
	private static final String SECOND_QUEUE = "deeppit";
	
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	@RabbitListener(queues = FIRST_QUEUE)
	public void onMessage(Message message) {
     String msg = message.toString();
     LOGGER.info("this conencted....");
     this.template.convertAndSend("/topic/messages" , msg);
     LOGGER.info(message.toString());
	}
	@RabbitListener(queues = SECOND_QUEUE)
	public void onMessage1s(Message message) {
     LOGGER.info("second queue listener.........");
     LOGGER.info(message.toString());
	}

}
