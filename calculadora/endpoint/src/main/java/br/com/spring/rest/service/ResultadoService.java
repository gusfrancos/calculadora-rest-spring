package br.com.spring.rest.service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.spring.rest.model.Operacao;

@Service
public class ResultadoService {
	
	private static Logger logger = LoggerFactory.getLogger(ResultadoService.class);

	private String valorResultado = null;
	private String uuid = "0";

	public void atualizar(Operacao operacao) {
		valorResultado = operacao.getValorFinal().toString();
		uuid = operacao.getUuid();
	}

	@Async
	public String obterJson(Operacao operacao) throws InterruptedException {
		logger.info("Aguardando resposta da fila para montar e devolver o JSON");
		boolean esperar = true;
		String json = null;
		int loop = 0;
		while (esperar) {
			json = valorResultado;
			esperar = Objects.isNull(valorResultado) || !this.uuid.equalsIgnoreCase(operacao.getUuid()) ? true : false;
			TimeUnit.MILLISECONDS.sleep(100);
			esperar = loop++<=50 ? true : false;
			logger.info(loop + " / " +  this.uuid + " / " +  operacao.getUuid());
		}
		
		if(!Objects.isNull(valorResultado)) {
			logger.info("Devolvendo o resultado:" + valorResultado);
			valorResultado = null;
			return resultadoJson(json);
		}
		logger.info("Aguardando resposta da fila para montar e devolver o JSON");
		return "Não foi possível recuperar o resultado";
	}
	
	private String resultadoJson(String texto) { 
		JSONObject json = new JSONObject();
		json.put("result",texto);
		return json.toString();
	}
}
