package com.example.demo.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private JmsTemplate jmsQueueTemplate;

	@Autowired
	private JmsTemplate jmsTopicTemplate;

	public void send(String destination, boolean topic, Object message) {
		LOGGER.info("START sending message='{}' to destination='{}' type: {}", message, destination,
				topic ? "topic" : "queue");
		try {
			if (topic) {
				jmsTopicTemplate.convertAndSend(destination, message);
			} else {
				jmsQueueTemplate.convertAndSend(destination, message);
			}
			LOGGER.info("SUCCESS sending message='{}' to destination='{}' type: {}", message, destination,
					topic ? "topic" : "queue");
		} catch (Exception e) {
			LOGGER.error("ERROR sending message='{}' to destination='{}' type: {}", message, destination,
					topic ? "topic" : "queue", e);
		}
	}
}
