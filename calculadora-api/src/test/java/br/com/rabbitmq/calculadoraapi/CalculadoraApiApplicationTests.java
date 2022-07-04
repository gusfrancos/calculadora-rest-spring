package br.com.rabbitmq.calculadoraapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;


class CalculadoraApiApplicationTests {

	@Test
	  void contextLoads(ApplicationContext context) {
	    assertThat(context).isNotNull();
	  }

}
