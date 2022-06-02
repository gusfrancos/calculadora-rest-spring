package br.com.rabbitmq.restapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class RestApiApplicationTests {

	@Test
	  void contextLoads(ApplicationContext context) {
	    assertThat(context).isNotNull();
	  }

}
