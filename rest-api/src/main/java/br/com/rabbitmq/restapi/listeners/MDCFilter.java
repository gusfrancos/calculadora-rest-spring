package br.com.rabbitmq.restapi.listeners;

import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.rabbitmq.restapi.configs.MDCFilterConfiguration;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class MDCFilter extends OncePerRequestFilter {

    private final String responseHeader;
    private final String mdcTokenKey;
    private final String mdcClientIpKey;
    private final String requestHeader;

    public MDCFilter() {
        responseHeader = MDCFilterConfiguration.DEFAULT_RESPONSE_TOKEN_HEADER;
        mdcTokenKey = MDCFilterConfiguration.DEFAULT_MDC_UUID_TOKEN_KEY;
        mdcClientIpKey = MDCFilterConfiguration.DEFAULT_MDC_CLIENT_IP_KEY;
        requestHeader = null;
    }

    public MDCFilter(final String responseHeader, final String mdcTokenKey, final String mdcClientIPKey, final String requestHeader) {
        this.responseHeader = responseHeader;
        this.mdcTokenKey = mdcTokenKey;
        this.mdcClientIpKey = mdcClientIPKey;
        this.requestHeader = requestHeader;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
            throws java.io.IOException, ServletException {
        try {
            final String token = extractToken(request);
            final String clientIP = extractClientIP(request);
            MDC.put(mdcClientIpKey, clientIP);
            MDC.put(mdcTokenKey, token);
            if (StringUtils.hasText(responseHeader)) {
                response.addHeader(responseHeader, token);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(mdcTokenKey);
            MDC.remove(mdcClientIpKey);
        }
    }

    private String extractToken(final HttpServletRequest request) {
        final String token;
        if (StringUtils.hasText(requestHeader) && StringUtils.hasText(request.getHeader(requestHeader))) {
            token = request.getHeader(requestHeader);
        } else {
            token = UUID.randomUUID().toString().toUpperCase();
        }
        return token;
    }

    private String extractClientIP(final HttpServletRequest request) {
        final String clientIP;
        if (request.getHeader("X-Forwarded-For") != null) {
            clientIP = request.getHeader("X-Forwarded-For").split(",")[0];
        } else {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }

    @Override
    protected boolean isAsyncDispatch(final HttpServletRequest request) {
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }
}


/**
 * @Getter/@Setter	Com estas anotações não se faz mais necessária a criação dos métodos de recuperação e configuração das propriedades das classes.
 * @ToString	Não há mais necessidade de iniciar um debugger para ver os campos. Basta deixar que o lombok gere o toString()
 * @EqualsAndHashCode	Método equals e hashCode são gerados automaticamente para os campos do objeto de forma fácil e simples
 * @Data	Todos juntos agora: Um atalho para @ToString, @EqualsAndHashCode,@Getter em todos os campos, e @Setter em todos os campos não-finais. Você ainda pode obter um construtor livre para inicializar seus campos finals!
 * @Cleanup	Gestão de recursos automática: Chame com segurança os métodos close()sem problemas.
 * @Synchronized	synchronized’s corretos. Não exponha seus locks.
 * @SneakThrows	Para lançar exceções onde antes não se era comum lançar.
 * 
 * 
 * @Component
 * A annotation básica que indica que uma classe vai ser gerenciada pelo container do Spring. 
 * Todas as annotations descritas acima são, na verdade, derivadas de @Component. 
 */


