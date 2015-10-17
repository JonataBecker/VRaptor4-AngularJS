package br.com.jonatabecker.interceptors;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.jonatabecker.annotations.NoCache;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonata Becker
 */
@Intercepts
@RequestScoped
public class NoCacheInterceptor {
    
    /** Objeto responsável pela resposta da requisição */
    @Inject
    private HttpServletResponse response;

    /**
     * Adiciona headers para não adicionar cache em página
     * 
     * @param stack 
     */
    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        // set the expires to past
        response.setHeader("Expires", "Wed, 31 Dec 1969 21:00:00 GMT");
        // no-cache headers for HTTP/1.1
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // no-cache headers for HTTP/1.1 (IE)
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // no-cache headers for HTTP/1.0
        response.setHeader("Pragma", "no-cache");
        stack.next();
    }
    
    /**
     * Executa apenas em páginas com a anotação NoCache
     * 
     * @param method
     * @return boolean
     */
    @Accepts
    public boolean accepts(ControllerMethod method) {
        return method.containsAnnotation(NoCache.class);
    }
}
