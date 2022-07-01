package br.com.rabbitmq.calculadoraapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableRabbit
@SpringBootApplication
public class CalculadoraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculadoraApiApplication.class, args);
	}

}
