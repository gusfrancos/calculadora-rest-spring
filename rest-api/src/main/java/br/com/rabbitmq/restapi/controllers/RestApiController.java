package br.com.rabbitmq.restapi.controllers;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rabbitmq.restapi.configs.MDCFilterConfiguration;
import br.com.rabbitmq.restapi.services.CalculadoraService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "calcular", description = "Endpoint realizações de cálculo")
@RestController
@RequestMapping(value = "/calcular")
public class RestApiController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private CalculadoraService calculadoraService;
	
	@ApiOperation(value = "Operação de soma")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resultado da soma"),
	@ApiResponse(code = 404, message = "Operação inválida") })
	@GetMapping(path ="somar", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BigDecimal> somar(HttpServletResponse response, @RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)  {
		LOGGER.info("[Rest API] - method somar [values {} | {}]", a, b);
		BigDecimal result = calculadoraService.somar(a, b, getToken(response));
		LOGGER.info("[Rest API] - method somar [values {} + {} = {}]", a, b, result);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * @param response
	 * @return
	 */
	private String getToken(HttpServletResponse response) {
		return response.getHeader(MDCFilterConfiguration.DEFAULT_RESPONSE_TOKEN_HEADER);
	}
}
