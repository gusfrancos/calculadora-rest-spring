package br.com.spring.rest.controller;

import java.math.BigDecimal;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.spring.rest.model.Operacao;
import br.com.spring.rest.service.CalculatorService;
import br.com.spring.rest.service.RabbitMQSender;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalculatorController {

	@Autowired
	private CalculatorService calculatorService;
	
	@Autowired
	RabbitMQSender rabbitMQSender;
	
	private static Logger logger = LoggerFactory.getLogger(CalculatorController.class);

	public void calcular(Operacao operacao) {
		logger.info("Iniciando a operação de calcular");
		BigDecimal resultado = null;
		
		resultado = operacao.getTipoOperacao().equals("Sm") ? calculatorService.somar(operacao.getValorA(), operacao.getValorB()) : resultado;
		resultado = Objects.isNull(resultado)  &&  operacao.getTipoOperacao().equals("Dm") ? calculatorService.diminuir(operacao.getValorA(), operacao.getValorB()) : resultado;
		resultado = Objects.isNull(resultado)  &&  operacao.getTipoOperacao().equals("Mt") ? calculatorService.multiplicar(operacao.getValorA(), operacao.getValorB()) : resultado;
		resultado = Objects.isNull(resultado)  && operacao.getTipoOperacao().equals("Dv") ? calculatorService.dividir(operacao.getValorA(), operacao.getValorB()) : resultado;

		operacao.setValorFinal(resultado);
		
		if(!Objects.isNull(resultado)) {
			rabbitMQSender.send(operacao);
			logger.info("Mensagem com resultado enviada para o RabbitMQ resposta-calculadora com sucesso");
		}
	}
}