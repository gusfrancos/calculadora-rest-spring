package br.com.rabbitmq.restapi.controllers;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rabbitmq.restapi.services.CalculadoraService;

@RestController
@RequestMapping(value = "/calcular/")
public class RestApiController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private CalculadoraService calculadoraService;
	
	@GetMapping(path ="/somar")
	public ResponseEntity<BigDecimal> somar(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)  {
		LOGGER.info("[Rest API] - method somar [values {} | {}]", a, b);
		BigDecimal result = calculadoraService.somar(a, b, "teste");
		LOGGER.info("[Rest API] - method somar [values {} + {} = {}]", a, b, result);
		return ResponseEntity.ok(result);
	}
	
	



}
