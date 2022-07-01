package br.com.rabbitmq.restapi.listeners;


import org.springframework.amqp.core.Message;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.rabbitmq.restapi.configs.RabbitMQConfiguration;

@Component
public class RestApiListener {
	private static final Logger log = LoggerFactory.getLogger(RestApiListener.class);

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME)
    public void onMessage(Message message) {
        process(message);
    }
    
    
    private void process(Message message) {
        String messageStr = new String(message.getBody(), Charset.defaultCharset());

        if ("dlq".equals(messageStr)) {
            throw new RuntimeException("to dead-letter");
        }

        log.info("message = [{}]", messageStr);
    }

}
