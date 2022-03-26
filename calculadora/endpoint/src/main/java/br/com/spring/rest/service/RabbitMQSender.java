package br.com.spring.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.spring.rest.model.Operacao;

@Service
public class RabbitMQSender {

	private static Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${calculadora.rabbitmq.exchange}")
	private String exchange;

	@Value("${calculadora.rabbitmq.routingkey}")
	private String routingkey;
	String kafkaTopic = "calculadora_topic";

	public void send(Operacao operacao) {
		logger.info("Enviando msg = " + operacao.toString());
		rabbitTemplate.convertAndSend(exchange, routingkey, operacao);
	}
}
