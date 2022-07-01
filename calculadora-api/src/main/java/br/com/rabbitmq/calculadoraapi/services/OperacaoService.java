package br.com.rabbitmq.calculadoraapi.services;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.rabbitmq.calculadoraapi.dtos.OperacaoCalculadoraDTO;

@Service
public class OperacaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperacaoService.class);

	public BigDecimal somar(OperacaoCalculadoraDTO operacaoDto) {
		LOGGER.info("[Project Calculator API] [OperacaoService] - Método Somar [OperacaoCalculatorDTO {}] Resultado:", operacaoDto.getValueA().add(operacaoDto.getValueB()));
		return operacaoDto.getValueA().add(operacaoDto.getValueB());
	}
}
