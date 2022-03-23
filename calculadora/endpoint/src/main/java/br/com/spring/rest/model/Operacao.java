package br.com.spring.rest.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Operacao.class)
public class Operacao {

	private BigDecimal valorA;
	private BigDecimal valorB;
	private String tipoOperacao;

	public BigDecimal getValorA() {
		return valorA;
	}

	public Operacao(BigDecimal valorA, BigDecimal valorB, String tipoOperacao) {
		super();
		this.valorA = valorA;
		this.valorB = valorB;
		this.tipoOperacao = tipoOperacao;
	}

	public void setValorA(BigDecimal valorA) {
		this.valorA = valorA;
	}

	public BigDecimal getValorB() {
		return valorB;
	}

	public void setValorB(BigDecimal valorB) {
		this.valorB = valorB;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	@Override
	public String toString() {
		return "Operacao [valorA=" + valorA + ", valorB=" + valorB + ", tipoOperacao=" + tipoOperacao + "]";
	}

}
