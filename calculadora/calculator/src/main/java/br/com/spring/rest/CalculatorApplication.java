package br.com.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CalculatorApplication {

	private static Logger logger = LoggerFactory.getLogger(CalculatorApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando a api que realiza calculo");
		SpringApplication.run(CalculatorApplication.class, args);
		logger.info("API de calculo pronta para realizar as requisições");
	}
	
	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

}
