package com.fiuba.taller.cors;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private void doFilter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain chain) throws IOException, ServletException {

        // Asumimos que cualquier request del frontend es seguro, así que le permitimos
        // cualquier header
        String allowedHeaders = request.getHeader("Access-Control-Request-Headers");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", allowedHeaders);

        // CORS preflight request es siempre HTTP OPTIONS, y no hay ws en nuestro dominio que use
        // este verbo. Entonces, sólo se propaga si no es OPTIONS
        if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
            chain.doFilter(request, response);
        }
    }

    @Override()
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse, filterChain);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}