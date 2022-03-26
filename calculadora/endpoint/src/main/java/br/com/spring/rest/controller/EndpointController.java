package br.com.spring.rest.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.rest.model.Operacao;
import br.com.spring.rest.service.RabbitMQSender;
import br.com.spring.rest.service.ResultadoService;

@RestController
@RequestMapping(value = "/calculadora-rabbitmq/")
public class EndpointController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@Autowired
	ResultadoService resultado;
	
	private static Logger logger = LoggerFactory.getLogger(EndpointController.class);

	
	@GetMapping(value = "/somar")
	public String somar(@RequestParam("a") BigDecimal a,@RequestParam("b") BigDecimal b) throws InterruptedException {
		Operacao operacao = new Operacao(a,b,"Sm");
		rabbitMQSender.send(operacao);
		logger.info("Mensagem Somar enviada para o RabbitMQ calculadora com sucesso", EndpointController.class.getNestHost());
		return resultado.obterJson(operacao);
	}
	
	@GetMapping(value = "/diminuir")
	public String diminuir(@RequestParam("a") BigDecimal a,@RequestParam("b") BigDecimal b) throws InterruptedException {
		Operacao operacao = new Operacao(a,b,"Dm");
		rabbitMQSender.send(operacao);
		logger.info("Mensagem Diminuir enviada para o RabbitMQ calculadora com sucesso");
		return resultado.obterJson(operacao);
	}
	
	@GetMapping(value = "/multiplicar")
	public String multiplicar(@RequestParam("a") BigDecimal a,@RequestParam("b") BigDecimal b) throws InterruptedException {
		Operacao operacao = new Operacao(a,b,"Mt");
		rabbitMQSender.send(operacao);
		logger.info("Mensagem Multiplicar enviada para o RabbitMQ calculadora com sucesso");
		return resultado.obterJson(operacao);
	}
	
	@GetMapping(value = "/dividir")
	public String dividir(@RequestParam("a") BigDecimal a,@RequestParam("b") BigDecimal b) throws InterruptedException {
		Operacao operacao = new Operacao(a,b,"Mt");
		rabbitMQSender.send(operacao);
		logger.info("Mensagem Dividir emviada para o RabbitMQ calculadora com sucesso");
		return resultado.obterJson(operacao);
	}
}
