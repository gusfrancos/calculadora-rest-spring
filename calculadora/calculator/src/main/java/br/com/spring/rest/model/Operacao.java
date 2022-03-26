package br.com.spring.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Operacao.class)
public class Operacao implements Serializable {
	
	private static final long serialVersionUID = 7154825716277105298L;
		
	private BigDecimal valorA;
	private BigDecimal valorB;
	private String tipoOperacao;
	private String uuid;
	private BigDecimal valorFinal;

	public Operacao(BigDecimal valorA, BigDecimal valorB, String tipoOperacao, String uuid, BigDecimal valorFinal) {
		super();
		this.valorA = valorA;
		this.valorB = valorB;
		this.tipoOperacao = tipoOperacao;
		this.uuid = uuid;
		this.valorFinal = valorFinal;
	}

	public BigDecimal getValorA() {
		return valorA;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	@Override
	public String toString() {
		return "Operacao [valorA=" + valorA + ", valorB=" + valorB + ", tipoOperacao=" + tipoOperacao + ", uuid=" + uuid
				+ ", valorFinal=" + valorFinal + "]";
	}
}
