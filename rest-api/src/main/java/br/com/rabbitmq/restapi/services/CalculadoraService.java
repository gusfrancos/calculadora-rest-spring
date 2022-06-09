package br.com.rabbitmq.restapi.services;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraService.class);

	/**
	 * Metodo responsavel por realizar a soma
	 * 
	 * @param a     - primeiro valor numerico
	 * @param b     - segundo valor numerico
	 * @param token - token enviado recebido pelo rest
	 * @return somatorio dos valores
	 */
	public BigDecimal somar(BigDecimal a, BigDecimal b, String token) {
		return (a.add(b));
	}
	
	/**
	 * Metodo responsavel por realizar a subtração
	 * 
	 * @param a     - primeiro valor numerico
	 * @param b     - segundo valor numerico
	 * @param token - token enviado recebido pelo rest
	 * @return somatorio dos valores
	 */
	public BigDecimal subtrair(BigDecimal a, BigDecimal b, String token) {
		return (a.subtract(b));
	}
	
	/**
	 * Metodo responsavel por realizar a subtração
	 * 
	 * @param a     - primeiro valor numerico
	 * @param b     - segundo valor numerico
	 * @param token - token enviado recebido pelo rest
	 * @return somatorio dos valores
	 */
	public BigDecimal dividir(BigDecimal a, BigDecimal b, String token) {
		return (a.divide(b));
	}
	
	/**
	 * Metodo responsavel por realizar a subtração
	 * 
	 * @param a     - primeiro valor numerico
	 * @param b     - segundo valor numerico
	 * @param token - token enviado recebido pelo rest
	 * @return somatorio dos valores
	 */
	public BigDecimal multiplicar(BigDecimal a, BigDecimal b, String token) {
		return (a.divide(b));
	}
}
