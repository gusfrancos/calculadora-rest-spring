package br.com.rabbitmq.restapi.controllers;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.rabbitmq.restapi.configs.MDCFilterConfiguration;
import br.com.rabbitmq.restapi.services.CalculadoraService;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiControllerTest {

	@InjectMocks // dependencia da classe que estou testando
	private RestApiController restApiController;

	@Mock
	private CalculadoraService calculadoraServiceMock;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
	}
	
	@Test
	public void NoParamsCalcularSomarShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/calcular/somar").contentType("application/json"))
			.andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void NoParamsCalcularMultiplicarShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/calcular/multiplicar").contentType("application/json"))
			.andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void NoParamsCalcularDividirShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/calcular/dividir").contentType("application/json"))
			.andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void NoParamsCalcularSubtrairShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/calcular/subtrair").contentType("application/json"))
			.andDo(print()).andExpect(status().isBadRequest());
	}
		
	@Test
	public void ParamCalcularSomarShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/calcular/somar").param("a", "10").param("b", "20").contentType("application/json"))
			.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void ParamCalcularSubtrairShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/calcular/subtrair").param("a", "10").param("b", "20").contentType("application/json"))
			.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void ParamCalcularDividirShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/calcular/dividir").param("a", "10").param("b", "20").contentType("application/json"))
			.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void ParamCalcularMultiplicarShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/calcular/multiplicar").param("a", "10").param("b", "20").contentType("application/json"))
			.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Teste da chamada de somar da controller")
	void list_ReturnsCorrectValueOfSum_WhenSuccessful() {
	    HttpServletResponse response = Mockito.mock( HttpServletResponse.class);
	    Mockito.when(response.getHeader(MDCFilterConfiguration.DEFAULT_RESPONSE_TOKEN_HEADER)).thenReturn("teste");
	    Mockito.when(calculadoraServiceMock.somar(new BigDecimal("1"), new BigDecimal("2"), "teste")).thenReturn(new BigDecimal("3"));
		ResponseEntity<BigDecimal> resultado = restApiController.somar(response, new BigDecimal("1"), new BigDecimal("2"));
		Assertions.assertThat(resultado.getBody().intValue()).isNotNull().isEqualTo(3);
		Assertions.assertThat(resultado.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Teste da chamada de subtrair da controller")
	void list_ReturnsCorrectValueOfSubtract_WhenSuccessful() {
	    HttpServletResponse response = Mockito.mock( HttpServletResponse.class);
	    Mockito.when(response.getHeader(MDCFilterConfiguration.DEFAULT_RESPONSE_TOKEN_HEADER)).thenReturn("teste");
	    Mockito.when(calculadoraServiceMock.subtrair(new BigDecimal("2"), new BigDecimal("1"), "teste")).thenReturn(new BigDecimal("1"));
		ResponseEntity<BigDecimal> resultado = restApiController.subtrair(response, new BigDecimal("2"), new BigDecimal("1"));
		Assertions.assertThat(resultado.getBody().intValue()).isNotNull().isEqualTo(1);
		Assertions.assertThat(resultado.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Teste da chamada de dividir da controller")
	void list_ReturnsCorrectValueOfDivide_WhenSuccessful() {
	    HttpServletResponse response = Mockito.mock( HttpServletResponse.class);
	    Mockito.when(response.getHeader(MDCFilterConfiguration.DEFAULT_RESPONSE_TOKEN_HEADER)).thenReturn("teste");
	    Mockito.when(calculadoraServiceMock.dividir(new BigDecimal("2"), new BigDecimal("1"), "teste")).thenReturn(new BigDecimal("2"));
		ResponseEntity<BigDecimal> resultado = restApiController.dividir(response, new BigDecimal("2"), new BigDecimal("1"));
		Assertions.assertThat(resultado.getBody().intValue()).isNotNull().isEqualTo(2);
		Assertions.assertThat(resultado.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Teste da chamada de multiplicar da controller")
	void list_ReturnsCorrectValueOfMultiply_WhenSuccessful() {
	    HttpServletResponse response = Mockito.mock( HttpServletResponse.class);
	    Mockito.when(response.getHeader(MDCFilterConfiguration.DEFAULT_RESPONSE_TOKEN_HEADER)).thenReturn("teste");
	    Mockito.when(calculadoraServiceMock.multiplicar(new BigDecimal("2"), new BigDecimal("1"), "teste")).thenReturn(new BigDecimal("2"));
		ResponseEntity<BigDecimal> resultado = restApiController.multiplicar(response, new BigDecimal("2"), new BigDecimal("1"));
		Assertions.assertThat(resultado.getBody().intValue()).isNotNull().isEqualTo(2);
		Assertions.assertThat(resultado.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
	}
	
}
