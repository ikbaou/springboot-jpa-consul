package com.example.demo.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.domain.EventMessage;

@Component
@ConditionalOnProperty(prefix = "jms.enabled", name = "enabled", matchIfMissing = false)
public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	/**
	 * receive messages from persistent, durable topic ${jms.topic.test}
	 * 
	 * @param eventMessage
	 */
	@JmsListener(destination = "${jms.topic.test}", 
				containerFactory = "topicJmsListenerContainerFactory", 
				subscription="${jms.topic.test}-${spring.application.custom.id}")
	public void onDurableTestTopicRespond(EventMessage eventMessage) {
		LOGGER.info("Received <" + eventMessage + ">");
	}
	
	/**
	 * receive messages from virtual topic ${jms.virtual.topic.test} as queue ${jms.virtual.topic.queue.test}
	 * 
	 * @param eventMessage
	 */
	@JmsListener(destination = "${jms.virtual.topic.queue.test}", 
				containerFactory = "queueJmsListenerContainerFactory", 
				subscription="${jms.virtual.topic.queue.test}-${spring.application.custom.id}")
	public void onVirtualTestTopicQueueRespond(EventMessage eventMessage) {
		LOGGER.info("Received <" + eventMessage + ">");
	}	

}
