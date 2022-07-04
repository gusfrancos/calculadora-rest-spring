package br.com.rabbitmq.calculadoraapi.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.rabbitmq.calculadoraapi.dtos.OperacaoCalculadoraDTO;

@Service
public class OperacaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperacaoService.class);

	public BigDecimal calcular(OperacaoCalculadoraDTO operacaoDto) {

		LOGGER.info("[Calculadora API] [OperacaoService] - método calcular [OperacaoCalculatorDTO {}]", operacaoDto.toString());
		switch (operacaoDto.getOperacao()) {
		case SUM:
			LOGGER.info("[Calculadora API] [OperacaoService] - Resultado SOMAR:", operacaoDto.getValueA().add(operacaoDto.getValueB()));
			return operacaoDto.getValueA().add(operacaoDto.getValueB());
		case SUBTRACT:
			LOGGER.info("[Calculadora API] [OperacaoService] - Resultado SUBTRAIR:", operacaoDto.getValueA().subtract(operacaoDto.getValueB()));
			return operacaoDto.getValueA().subtract(operacaoDto.getValueB());
		case MULTIPLY:
			LOGGER.info("[Calculadora API] [OperacaoService] - Resultado MULTIPLICAR:", operacaoDto.getValueA().multiply(operacaoDto.getValueB()));
			return operacaoDto.getValueA().multiply(operacaoDto.getValueB());
		case DIVIDE:
			LOGGER.info("[Calculadora API] [OperacaoService] - Resultado DIVIDIR:", operacaoDto.getValueA().divide(operacaoDto.getValueB(), RoundingMode.HALF_EVEN));
			return operacaoDto.getValueA().divide(operacaoDto.getValueB(), RoundingMode.HALF_EVEN);
		default:
			LOGGER.info("[Calculadora API] [OperacaoService] - método calcular [OperacaoCalculatorDTO {}], Operação não localizada", operacaoDto.toString());

			return BigDecimal.ZERO;
		}

	}
}
