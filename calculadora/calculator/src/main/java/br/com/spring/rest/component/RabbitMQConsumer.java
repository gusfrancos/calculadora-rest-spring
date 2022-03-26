package br.com.spring.rest.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.spring.rest.controller.CalculatorController;
import br.com.spring.rest.model.Operacao;

@Component
public class RabbitMQConsumer {

	@Autowired
	private CalculatorController calculatorControler;
	
	private static Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);


	@RabbitListener(queues = "${calculadora.rabbitmq.queue}")
	public void receivedMessage(Operacao operacao) {
		logger.info("Mensagem recebida do RabbitMQ (calculator): " + operacao);
		//Operacao operacao = new Gson().fromJson(json, Operacao.class);
		calculatorControler.calcular(operacao);
	}
}