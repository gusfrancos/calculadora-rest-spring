package br.com.rabbitmq.restapi.dtos;

import java.math.BigDecimal;

import br.com.rabbitmq.restapi.enums.OperacaoEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OperacaoRestDTO {
	
	private BigDecimal valueA;
	
	private BigDecimal valueB;
	
	private OperacaoEnum operacao;
	
}

/**
* @Getter/@Setter	Com estas anotações não se faz mais necessária a criação dos métodos de recuperação e configuração das propriedades das classes.
* @ToString	Não há mais necessidade de iniciar um debugger para ver os campos. Basta deixar que o lombok gere o toString()
* @EqualsAndHashCode	Método equals e hashCode são gerados automaticamente para os campos do objeto de forma fácil e simples
* @Data	Todos juntos agora: Um atalho para @ToString, @EqualsAndHashCode,@Getter em todos os campos, e @Setter em todos os campos não-finais. Você ainda pode obter um construtor livre para inicializar seus campos finals!
* @Cleanup	Gestão de recursos automática: Chame com segurança os métodos close()sem problemas.
* @Synchronized	synchronized’s corretos. Não exponha seus locks.
**/