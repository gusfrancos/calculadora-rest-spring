package br.com.spring.rest.service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class ResultadoService {
	
	private static Logger logger = LoggerFactory.getLogger(ResultadoService.class);

	static String valorResultado = null;

	public static void atualizar(String valor) {
		valorResultado = valor;
	}

	public static String obterJson() throws InterruptedException {
		logger.info("Aguardando resposta da fila para montar e devolver o JSON");
		boolean esperar = true;
		String json = null;
		int loop = 0;
		while (esperar) {
			json = valorResultado;
			esperar = loop++<=50 ? true : false;
			esperar = Objects.isNull(valorResultado) ? true : false;
			TimeUnit.MILLISECONDS.sleep(100);
		}
		
		if(!Objects.isNull(valorResultado)) {
			logger.info("Devolvendo o resultado:" + valorResultado);
			valorResultado = null;
			return resultadoJson(json);
		}
		logger.info("Aguardando resposta da fila para montar e devolver o JSON");
		return "Não foi possível recuperar o resultado";
	}
	
	static String resultadoJson(String texto) { 
		JSONObject json = new JSONObject();
		json.put("result",texto);
		return json.toString();
	}
}
