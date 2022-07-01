package br.com.rabbitmq.calculadoraapi.listeners;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rabbitmq.calculadoraapi.configs.RabbitMQConfiguration;
import br.com.rabbitmq.calculadoraapi.dtos.OperacaoCalculadoraDTO;
import br.com.rabbitmq.calculadoraapi.services.OperacaoService;

public class CalculadoraApiListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraApiListener.class);
	@Autowired
	private OperacaoService operacaoService;
	
    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME)
    public BigDecimal receive(Message message , @Header("token") String token) {
    	
    	String fileBody= new String(message.getBody());
    	
    	LOGGER.info("[Project Calculadora API] - method receive [json {} | token {}]", fileBody, token);
		try {
			ObjectMapper mapper = new ObjectMapper();
			OperacaoCalculadoraDTO operacaoDto = mapper.readValue(fileBody, OperacaoCalculadoraDTO.class);
			LOGGER.info("[Project Calculator API] - method receive [OperacaoCalculatorDTO {}]", operacaoDto.toString());
			return operacaoService.somar(operacaoDto);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Erro ao converter objeto em json", e);
		}
    }
}
