package br.com.spring.rest.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import br.com.spring.rest.exception.BadRequestException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculatorService {
	public BigDecimal somar(BigDecimal parcela, BigDecimal parcelaSomar) {
		return parcela.add(parcelaSomar);
	}

	public BigDecimal diminuir(BigDecimal minuendo, BigDecimal subtraendo) {
		return minuendo.subtract(subtraendo);
	}

	public BigDecimal multiplicar(BigDecimal multiplicador, BigDecimal multiplicando) {
		return multiplicador.multiply(multiplicador);
	}

	public BigDecimal dividir(BigDecimal dividendo, BigDecimal divisor) {
		if (divisor.equals(BigDecimal.ZERO)) {
	         throw new BadRequestException("Não é permitido divisão por zero");
	      }
	      return dividendo.divide(divisor, RoundingMode.HALF_EVEN);
	}
}
