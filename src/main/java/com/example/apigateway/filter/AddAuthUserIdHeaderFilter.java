package com.example.apigateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddAuthUserIdHeaderFilter implements GatewayFilterFactory<AddAuthUserIdHeaderFilter.Config> {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                try {
                    String token = authHeader.substring(7);
                    Claims claims = Jwts.parser()
                            .setSigningKey(jwtSecret.getBytes())
                            .parseClaimsJws(token)
                            .getBody();

                    String userId = claims.getSubject(); // və ya claims.get("userId").toString()
                    if (userId != null) {
                        exchange = exchange.mutate()
                                .request(r -> r.headers(headers -> headers.set("X-Auth-User-Id", userId)))
                                .build();
                    }
                } catch (Exception e) {
                    log.warn("Failed to parse JWT: {}", e.getMessage());
                }
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {
    }
}
