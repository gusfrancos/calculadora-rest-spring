package br.com.spring.rest.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.rest.model.Operacao;
import br.com.spring.rest.service.RabbitMQSender;
import br.com.spring.rest.service.Resultado;

@RestController
@RequestMapping(value = "/calculadora-rabbitmq/")
public class EndpointController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@GetMapping(value = "/somar")
	public String somar(@RequestParam("a") BigDecimal a,@RequestParam("b") BigDecimal b) throws InterruptedException {
		rabbitMQSender.send(new Operacao(a,b,"Sm"));
		System.out.println("Mensagem Somar emviada para o RabbitMQ calculadora com sucesso");
		return Resultado.obterJson();
	}
	
	@GetMapping(value = "/diminuir")
	public String diminuir(@RequestParam("a") BigDecimal a,@RequestParam("b") BigDecimal b) throws InterruptedException {
		rabbitMQSender.send(new Operacao(a,b,"Dm"));
		System.out.println(" Mensagem Diminuir emviada para o RabbitMQ calculadora com sucesso");
		return Resultado.obterJson();
	}
	
	@GetMapping(value = "/multiplicar")
	public String multiplicar(@RequestParam("a") BigDecimal a,@RequestParam("b") BigDecimal b) throws InterruptedException {
		rabbitMQSender.send(new Operacao(a,b,"Mt"));
		System.out.println("Mensagem Multiplicar emviada para o RabbitMQ calculadora com sucesso");
		return Resultado.obterJson();
	}
	
	@GetMapping(value = "/dividir")
	public String dividir(@RequestParam("a") BigDecimal a,@RequestParam("b") BigDecimal b) throws InterruptedException {
		rabbitMQSender.send(new Operacao(a,b,"Dv"));
		System.out.println("Mensagem Dividir emviada para o RabbitMQ calculadora com sucesso");
		return Resultado.obterJson();
	}
}
