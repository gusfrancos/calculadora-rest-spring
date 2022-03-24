package br.com.spring.rest.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.spring.rest.service.ResultadoService;

@Component
public class RabbitMQConsumer {
	
	private static Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@RabbitListener(queues = "${resposta.calculadora.rabbitmq.queue}")
	public void receivedMessage(@Payload String json) {
		logger.info("Mensagem recebida do RabbitMQ (endpoint): " + json);
		ResultadoService.atualizar(json);
	}
}