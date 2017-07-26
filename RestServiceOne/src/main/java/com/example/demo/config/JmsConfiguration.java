package com.example.demo.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
@ConditionalOnProperty(prefix = "jms.enabled", name = "enabled", matchIfMissing = false)
public class JmsConfiguration {
	
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;		
	
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setTrustAllPackages(true);
        return connectionFactory;
    }
    
    @Bean
    public ConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(connectionFactory());
        connectionFactory.setSessionCacheSize(10);
        return connectionFactory;
    }	
    
    @Bean
    public DefaultJmsListenerContainerFactory queueJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory f = new  DefaultJmsListenerContainerFactory();
        f.setConnectionFactory(cachingConnectionFactory());
        f.setSessionTransacted(true);
        f.setPubSubDomain(false);
        return f;
    }    
    
    @Bean
    public DefaultJmsListenerContainerFactory topicJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory f = new  DefaultJmsListenerContainerFactory();
        f.setConnectionFactory(cachingConnectionFactory());
        f.setSessionTransacted(true);
        f.setSubscriptionDurable(true);
        f.setPubSubDomain(true);
        return f;
    }    
    
    @Bean
    public JmsTemplate jmsQueueTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(cachingConnectionFactory());
        template.setPubSubDomain(false);
        template.setDeliveryPersistent(true);
        return template;
    }    
    
    @Bean
    public JmsTemplate jmsTopicTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(cachingConnectionFactory());
        template.setPubSubDomain(true);
        template.setDeliveryPersistent(true);
        return template;
    }     
		
}
