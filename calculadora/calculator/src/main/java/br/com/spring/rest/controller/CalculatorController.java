package br.com.spring.rest.controller;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

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

	public void calcular(String json) {
		BigDecimal resultado = null;
		Operacao operacao = new Gson().fromJson(json, Operacao.class);
		
		System.out.println(operacao.toString());
		
		resultado = operacao.getTipoOperacao().equals("Sm") ? calculatorService.somar(operacao.getValorA(), operacao.getValorB()) : resultado;
		resultado = Objects.isNull(resultado)  &&  operacao.getTipoOperacao().equals("Dm") ? calculatorService.diminuir(operacao.getValorA(), operacao.getValorB()) : resultado;
		resultado = Objects.isNull(resultado)  &&  operacao.getTipoOperacao().equals("Mt") ? calculatorService.multiplicar(operacao.getValorA(), operacao.getValorB()) : resultado;
		resultado = Objects.isNull(resultado) && operacao.getTipoOperacao().equals("Dv") ? calculatorService.dividir(operacao.getValorA(), operacao.getValorB()) : resultado;

		if(!Objects.isNull(resultado)) {
			rabbitMQSender.send(resultado.toString());
		}
		
		System.out.println("Resultado Final:" + resultado);
		
	}
}