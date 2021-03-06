package br.com.rabbitmq.restapi.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rabbitmq.restapi.listeners.MDCFilter;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "calculadora.slf4jfilter")
public class MDCFilterConfiguration {

    public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "Response_Token";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "MDCFilter.UUID";
    public static final String DEFAULT_MDC_CLIENT_IP_KEY = "MDCFilter.ClientIP";

    

    @Bean
    public FilterRegistrationBean<MDCFilter> servletRegistrationBean() {
        final FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
        final MDCFilter filter = new MDCFilter(DEFAULT_RESPONSE_TOKEN_HEADER, DEFAULT_MDC_UUID_TOKEN_KEY, DEFAULT_MDC_CLIENT_IP_KEY, null);
        registrationBean.setFilter(filter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}

/**
* @ Data
* Essa anotação é a combinação das anotações: @ ToString, @EqualsAndHashCode, @ Getter e @ Setter. 
* Também fornece a geração de construtores, adicionando um construtor público que leva qualquer campo @NonNull 
* ou final como parâmetros.
*
* @Configuration
* É uma annotation que indica que determinada classe possui métodos que expõe novos beans.
* 
* @ConfigurationProperties
* mapear todas as propriedades definidas no application.properties arquivo para um POJO
*/
