package br.com.rabbitmq.restapi.controllers;

import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.devtools.remote.server.Dispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.rabbitmq.restapi.configs.MDCFilterConfiguration;
import br.com.rabbitmq.restapi.services.CalculadoraService;

@ExtendWith(SpringExtension.class)
public class RestApiControllerTest {

	@InjectMocks // dependencia da classe que estou testando
	private RestApiController restApiController;

	@Mock
	private CalculadoraService calculadoraServiceMock;

	@BeforeEach
	void setUp() {
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
}
