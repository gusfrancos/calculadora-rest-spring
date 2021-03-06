package br.com.rabbitmq.restapi.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	public static final String EXCHANGE_NAME = "calculadora-exchange";

	public static final String ROUTING_KEY = "calculadora-routingkey";

	public static final String QUEUE_NAME = "calculadora-queue";
	
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

/**
* @Configuration
* É uma annotation que indica que determinada classe possui métodos que expõe novos beans.
*/



