package br.com.rabbitmq.restapi.services;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rabbitmq.restapi.configs.RabbitMQConfiguration;
import br.com.rabbitmq.restapi.dto.OperacaoRestDTO;
import br.com.rabbitmq.restapi.enums.OperacaoEnum;

@Service
public class CalculadoraService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraService.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * Metodo responsavel por realizar a soma 
	 * @param a - primeiro valor numerico
	 * @param b - segundo valor numerico
	 * @param token - token enviado recebido pelo rest
	 * @return somatorio dos valores
	 */
	public BigDecimal somar(BigDecimal a, BigDecimal b, String token) {
		Message message = getMessage(a, b, token, OperacaoEnum.SUM);
		
		return (BigDecimal) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.EXCHANGE_NAME,
				RabbitMQConfiguration.ROUTING_KEY, message);
	}
	
	/**
	 * Metodo responsavel por realizar a subtração
	 * 
	 * @param a     - primeiro valor numerico
	 * @param b     - segundo valor numerico
	 * @param token - token enviado recebido pelo rest
	 * @return somatorio dos valores
	 */
	public BigDecimal subtrair(BigDecimal a, BigDecimal b, String token) {
		return (a.subtract(b));
	}
	
	/**
	 * Metodo responsavel por realizar a subtração
	 * 
	 * @param a     - primeiro valor numerico
	 * @param b     - segundo valor numerico
	 * @param token - token enviado recebido pelo rest
	 * @return somatorio dos valores
	 */
	public BigDecimal dividir(BigDecimal a, BigDecimal b, String token) {
		return (a.divide(b));
	}
	
	/**
	 * Metodo responsavel por realizar a subtração
	 * 
	 * @param a     - primeiro valor numerico
	 * @param b     - segundo valor numerico
	 * @param token - token enviado recebido pelo rest
	 * @return somatorio dos valores
	 */
	public BigDecimal multiplicar(BigDecimal a, BigDecimal b, String token) {
		return (a.multiply(b));
	}
	
	/**
	 * Metodo 
	 * @param a - primeiro valor numerico
	 * @param b - segundo valor numerico
	 * @param token - 
	 * @param operacao - operacao da calculadora
	 * @return {@link Message} objeto de mensagem
	 */
	private Message getMessage(BigDecimal a, BigDecimal b, String token, OperacaoEnum operacao) {
		String json = getJsonOfOperacaoDto(a, b, operacao);
		LOGGER.info("[Project Rest API] - method getMessage [json {} | token {}]", json, token);
		return MessageBuilder.withBody(json.getBytes()).setHeader("token", token).build();
	}
	
	/**
	 * @param a
	 * @param b
	 * @param operacao
	 * @return
	 */
	private String getJsonOfOperacaoDto(BigDecimal a, BigDecimal b, OperacaoEnum operacao) {
		try {
			OperacaoRestDTO operacaoDto = OperacaoRestDTO.builder().valueA(a).valueB(b).operacao(operacao).build();
			LOGGER.info("[Project Rest API] - method getJsonOfOperacaoDto [OperacaoRestDTO {}]", operacaoDto.toString());
			return new ObjectMapper().writeValueAsString(operacaoDto);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("erro ao converter objeto para json");
		}
	}
}
