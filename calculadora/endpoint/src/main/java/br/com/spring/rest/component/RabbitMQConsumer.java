package br.com.spring.rest.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.spring.rest.service.Resultado;

@Component
public class RabbitMQConsumer {

	@RabbitListener(queues = "${resposta.calculadora.rabbitmq.queue}")
	public void receivedMessage(@Payload String json) {
		
		System.out.println("Mensagem recebida do RabbitMQ (endpoint): " + json);
		
		Resultado.atualizar(json);
		
	}
}