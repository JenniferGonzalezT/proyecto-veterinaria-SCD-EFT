package com.duoc.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity()
@Configuration
@Profile("default")
class WebSecurityConfig {

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
            // 1. CSRF deshabilitado (API REST con JWT)
            .csrf((csrf) -> csrf.disable())
            
            // 2. Forzar que la API sea STATELESS (No creará JSESSIONID)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // 3. Cabeceras de seguridad para mitigar alertas de ZAP
            .headers(headers -> headers
                // Mitiga "Falta encabezado X-Content-Type-Options"
                .contentTypeOptions(Customizer.withDefaults())
                
                // Mitiga "Falta de cabecera Anti-Clickjacking"
                // Nota: Usamos DENY porque una API backend NUNCA debería ser incrustada en un iframe.
                .frameOptions(frame -> frame.deny()) 
                
                // Mitiga "Strict-Transport-Security Header No Establecido"
                .httpStrictTransportSecurity(hsts -> hsts
                    .includeSubDomains(true)
                    .maxAgeInSeconds(31536000)
                )
                
                // Mitiga "Cabecera Content Security Policy (CSP) no configurada"
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; img-src 'self' data:;")
                )
            )

            // 4. Reglas de autorización
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
                .requestMatchers(HttpMethod.GET, Constants.LOGIN_URL).permitAll()
                .requestMatchers(HttpMethod.GET, "/pets/**").permitAll()
                
                // Permitir acceso a la documentación de Swagger/OpenAPI si la utilizas
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                
                .anyRequest().authenticated()
            )
            
            // 5. Filtro de JWT
            .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }
}