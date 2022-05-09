package br.com.spring.rest.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spring.rest.model.Operacao;
import br.com.spring.rest.service.ResultadoService;

@Component
public class RabbitMQConsumer {
	
	private static Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	@Autowired
	ResultadoService resultado;

	@RabbitListener(queues = "${resposta.calculadora.rabbitmq.queue}")
	public void receivedMessage(Operacao operacao) {
		logger.info("Mensagem recebida do RabbitMQ (endpoint): " + operacao);
		resultado.atualizar(operacao);
	}
}