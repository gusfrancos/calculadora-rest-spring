package br.com.rabbitmq.calculadoraapi.dtos;

import java.math.BigDecimal;

import br.com.rabbitmq.calculadoraapi.enums.OperacaoEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OperacaoCalculadoraDTO {
	private BigDecimal valueA;

	private BigDecimal valueB;

	private OperacaoEnum operacao;
}
