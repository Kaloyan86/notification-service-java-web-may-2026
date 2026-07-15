package app.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import app.exception.InvalidApiKeyException;
import app.exception.MissingApiKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private static final String X_API_KEY = "X-API-Key";
    @Value("${notification.service.api-key}")
    private String validApiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String apiKey = request.getHeader(X_API_KEY);

        if (apiKey == null || apiKey.isBlank()) {
            throw new MissingApiKeyException("Missing API Key header!");
        }

        if (!apiKey.equals(validApiKey)) {
            throw new InvalidApiKeyException("Invalid API Key!");
        }

        Authentication authentication = new ApiKeyAuthentication(apiKey);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);


    }
}
