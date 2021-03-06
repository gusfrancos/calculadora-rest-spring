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
		LOGGER.info("[Rest API] [RestApiController] - Método somar [valores {} | {}]", a, b);
		BigDecimal result = calculadoraService.somar(a, b, getToken(response));
		LOGGER.info("[Rest API] [RestApiController] - Método somar [values {} + {} = {}]", a, b, result);
		return ResponseEntity.ok(result);
	}
	
	
	@ApiOperation(value = "Operação de subtração")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resultado da subtração"),
	@ApiResponse(code = 404, message = "Operação inválida") })
	@GetMapping(path ="subtrair", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BigDecimal> subtrair(HttpServletResponse response, @RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)  {
		LOGGER.info("[Rest API] [RestApiController] - método subtrair [values {} | {}]", a, b);
		BigDecimal result = calculadoraService.subtrair(a, b, getToken(response));
		LOGGER.info("[Rest API] [RestApiController] - método subtrair [values {} + {} = {}]", a, b, result);
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation(value = "Operação de divisão")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resultado da divisão"),
	@ApiResponse(code = 404, message = "Operação inválida") })
	@GetMapping(path ="dividir", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BigDecimal> dividir(HttpServletResponse response, @RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)  {
		LOGGER.info("[Rest API] [RestApiController] - método dividir [values {} | {}]", a, b);
		BigDecimal result = calculadoraService.dividir(a, b, getToken(response));
		LOGGER.info("[Rest API] [RestApiController] - método dividir [values {} + {} = {}]", a, b, result);
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation(value = "Operação de Multiplicação")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resultado da Multiplicação"),
	@ApiResponse(code = 404, message = "Operação inválida") })
	@GetMapping(path ="multiplicar", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BigDecimal> multiplicar(HttpServletResponse response, @RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)  {
		LOGGER.info("[Rest API] [RestApiController] - método multiplicar [values {} | {}]", a, b);
		BigDecimal result = calculadoraService.multiplicar(a, b, getToken(response));
		LOGGER.info("[Rest API] [RestApiController] - método multiplicar [values {} + {} = {}]", a, b, result);
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


/*
* @Autowired
* Anotação utilizada para marcar o ponto de injeção na sua classe. 
* Você pode colocar ela sobre atributos ou sobre o seu construtor com argumentos.
* delega ao Spring Boot a inicialização do objeto;
* 
* @RestController permite definir um controller com características REST;
* @RequestMapping permite definir uma rota. Caso não seja informado o método HTTP da rota, ela será definida para todos os métodos.
* @PathVariable indica que o valor da variável virá de uma informação da rota;
* @RequestBody indica que o valor do objeto virá do corpo da requisição;
* @Valid indica que os dados recebidos devem ser validados.
* @GetMapping é composta da @RequestMapping. Ela é utilizada para mapear requisições HTTP GET. Essa notação é muito importante quando a pessoa desenvolvedora precisa fazer requisições via postman, pois nessa plataforma é necessário informar qual o tipo do método está sendo esperado.
* 
* OPENAPI:
* @RequestBody  - Representa o corpo da solicitação em uma operação.
* @ApiResponse  - Representa a resposta em um operação.
* @Tag	        - Representa tags para uma operação ou para a definição OpenAPI.
*
* @ApiOperation - Para cada um de nossos endpoints de operação, podemos usar o @ApiOperation anotação para descrever o endpoint e seu tipo de resposta
* 
*/

