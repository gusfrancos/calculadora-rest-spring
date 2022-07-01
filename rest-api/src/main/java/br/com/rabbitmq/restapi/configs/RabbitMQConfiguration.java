package br.com.rabbitmq.restapi.configs;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	public static final String EXCHANGE_NAME = "calculadora-exchange";

	public static final String ROUTING_KEY = "calculadora-routingkey";

	public static final String QUEUE_NAME = "calculadora-queue";
	
	
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.1.65");
//		connectionFactory.setUsername("guest");
//		connectionFactory.setPassword("guest");
//		return connectionFactory;
//	}
//
//	@Bean
//	public AmqpAdmin amqpAdmin() {
//		return new RabbitAdmin(connectionFactory());
//	}
	
	
	@Bean
	Queue messagesQueue() {
		return QueueBuilder.durable(QUEUE_NAME).build();
	}

	@Bean
	DirectExchange messagesExchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	Binding bindingMessages() {
		return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(QUEUE_NAME);
	}

}
