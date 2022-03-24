package br.com.spring.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
	
	private static Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${resposta.calculadora.rabbitmq.exchange}")
	private String exchange;

	@Value("${resposta.calculadora.rabbitmq.routingkey}")
	private String routingkey;
	String kafkaTopic = "resposta_calculadora_topic";

	public void send(String resultado) {
		logger.info("Enviando msg = " + resultado);
		amqpTemplate.convertAndSend(exchange, routingkey, resultado);
	}
}
