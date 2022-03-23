package br.com.spring.rest.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.spring.rest.controller.CalculatorController;

@Component
public class RabbitMQConsumer {

	@Autowired
	private CalculatorController calculatorControler;

	@RabbitListener(queues = "${calculadora.rabbitmq.queue}")
	public void receivedMessage(@Payload String json) {
		System.out.println("Mensagem recebida do RabbitMQ: " + json);
		calculatorControler.calcular(json);
	}
}