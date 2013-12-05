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
                          HttpServletResponse response) {

        // CORS preflight request es siempre HTTP OPTIONS
        if (request.getMethod().equals("OPTIONS")) {
            // Asumimos que cualquier request del frontend es seguro, así que le permitimos
            // cualquier header
            String allowedHeaders = request.getHeader("Access-Control-Request-Headers");
            response.addHeader("Access-Control-Allow-Origin", "http://localhost:5000");
            response.addHeader("Access-Control-Allow-Headers", allowedHeaders);
        }
    }

    @Override()
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}