package br.com.spring.rest;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EndpointApplication {

	private static Logger logger = LoggerFactory.getLogger(EndpointApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando a api com endpoints");
		SpringApplication.run(EndpointApplication.class, args);
		logger.info("API com endpoints pronta para receber as requisições");
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
}
