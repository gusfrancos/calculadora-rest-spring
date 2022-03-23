package br.com.spring.rest.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.spring.rest.model.Operacao;

@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${calculadora.rabbitmq.exchange}")
	private String exchange;

	@Value("${calculadora.rabbitmq.routingkey}")
	private String routingkey;
	String kafkaTopic = "calculadora_topic";

	public void send(Operacao operacao) {
		amqpTemplate.convertAndSend(exchange, routingkey, operacao);
		System.out.println("Enviando msg = " + operacao);
	}
}
