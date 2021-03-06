package br.com.rabbitmq.calculadoraapi.listeners;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rabbitmq.calculadoraapi.configs.RabbitMQConfiguration;
import br.com.rabbitmq.calculadoraapi.dtos.OperacaoCalculadoraDTO;
import br.com.rabbitmq.calculadoraapi.services.OperacaoService;

@Component
public class CalculadoraApiListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraApiListener.class);
	@Autowired
	private OperacaoService operacaoService;
	
    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME)
    public BigDecimal receive(Message message , @Header("token") String token) {
    	
    	String fileBody= new String(message.getBody());
    	
    	LOGGER.info("[Project Calculadora API] [CalculadoraApiListener] - Método RECEIVE [json {} | token {}]", fileBody, token);
		try {
			ObjectMapper mapper = new ObjectMapper();
			OperacaoCalculadoraDTO operacaoDto = mapper.readValue(fileBody, OperacaoCalculadoraDTO.class);
			LOGGER.info("[Project Calculadora API] [CalculadoraApiListener] - Método RECEIVE [OperacaoCalculadorarDTO {}]", operacaoDto.toString());
			return operacaoService.calcular(operacaoDto);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("[Project Calculadora API] [CalculadoraApiListener] - Método RECEIVE - Erro ao converter objeto em json", e);
		}
    }
}

/**
* @Component
* A annotation básica que indica que uma classe vai ser gerenciada pelo container do Spring. 
* Todas as annotations descritas acima são, na verdade, derivadas de @Component.
* 
* @Autowired
* Anotação utilizada para marcar o ponto de injeção na sua classe. Você pode colocar ela sobre atributos ou sobre o seu construtor com argumentos.  
*/
