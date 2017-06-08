package io.as.todo.store.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;

/**
 * A filter to measure & log response time of a request. This is the first filter in line; so, it measures everything.
 */
@Component
public class LogResponseTimeFilter implements Filter, Ordered
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LogResponseTimeFilter.class);

    private int order = Ordered.HIGHEST_PRECEDENCE;

    @Override
    public int getOrder()
    {
        return order;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        LOGGER.info("Initializing request filter.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String path = null;
        String queryString = null;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if (servletRequest instanceof HttpServletRequest)
        {
            path = httpServletRequest.getRequestURI();
            queryString = httpServletRequest.getQueryString();
        }

        Instant arrivedAt = Instant.now(Clock.systemUTC());
        filterChain.doFilter(servletRequest, servletResponse);
        Instant leftAt = Instant.now(Clock.systemUTC());

        int durationMillis = (int) (leftAt.toEpochMilli() - arrivedAt.toEpochMilli());
        int status = ((HttpServletResponse) servletResponse).getStatus();
        String method = httpServletRequest.getMethod();

        LOGGER.info("OUT (HTTP {}) -> ({} ms) -> {} {}. IP -> {}", status, durationMillis, method, path, getIpString(httpServletRequest));
    }

    @Override
    public void destroy()
    {
        LOGGER.info("Destroying request filter.");
    }

    private String getIpString(HttpServletRequest httpServletRequest)
    {
        if (httpServletRequest == null)
        {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(').append(httpServletRequest.getRemoteAddr()).append(')');
        String xRealIp = httpServletRequest.getHeader("X-Real-IP");
        if (xRealIp != null)
        {
            stringBuilder.append(" (").append(xRealIp).append(')');
        }
        String xff = httpServletRequest.getHeader("X-Forwarded-For");
        if (xff != null)
        {
            stringBuilder.append(" (").append(xff).append(')');
        }
        return stringBuilder.toString();
    }
}
