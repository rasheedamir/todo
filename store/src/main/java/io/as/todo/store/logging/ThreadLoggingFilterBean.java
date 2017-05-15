package io.as.todo.store.logging;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ThreadLoggingFilterBean extends GenericFilterBean
{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        try
        {
            boolean logEverythingForThisRequest = "on".equalsIgnoreCase(request.getParameter("trace"));
            ThreadLoggingSupport.logEverything(logEverythingForThisRequest);
            chain.doFilter(request, response);
        }
        finally
        {
            ThreadLoggingSupport.cleanup();
        }
    }

}
