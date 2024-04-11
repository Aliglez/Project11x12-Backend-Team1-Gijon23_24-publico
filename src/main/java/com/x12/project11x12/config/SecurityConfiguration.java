package com.x12.project11x12.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.x12.project11x12.security.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${api-endpoint}")
    String endpoint;

    @Autowired
    private AuthenticationEntryPoint CustomAuthenticationEntryPoint;

    JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
            this.jpaUserDetailsService = jpaUserDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .logout(out -> out
                        .logoutUrl(endpoint + "/logout")
                        .deleteCookies("JSESSIONID"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, endpoint + "/login").hasAnyRole("USER","ADMIN") 
                        .requestMatchers(HttpMethod.POST, endpoint + "/register").permitAll()    
                        .requestMatchers(HttpMethod.GET, endpoint + "/scholarship").permitAll()
                        .requestMatchers(HttpMethod.POST, endpoint + "/scholarship").permitAll()
                        .requestMatchers(HttpMethod.DELETE, endpoint + "/scholarship/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, endpoint + "/profile").permitAll()
                        .requestMatchers(HttpMethod.POST, endpoint + "/profile").permitAll()
                        .requestMatchers(HttpMethod.GET, endpoint + "/camps").permitAll()
                        .requestMatchers(HttpMethod.POST, endpoint + "/camps").permitAll()
                        .requestMatchers(HttpMethod.GET, endpoint + "/participants").permitAll()
                        .requestMatchers(HttpMethod.POST, endpoint + "/participants").permitAll()
                        .requestMatchers(HttpMethod.PUT, endpoint + "/participants/**").permitAll()
                        .requestMatchers(HttpMethod.POST, endpoint + "/camps").permitAll()                      
                        .requestMatchers(HttpMethod.GET, endpoint + "/inscriptions").hasRole("ADMIN")
                        .anyRequest().authenticated())                
                .userDetailsService(jpaUserDetailsService)
                .httpBasic(basic -> basic.authenticationEntryPoint(CustomAuthenticationEntryPoint))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));        
        configuration.setAllowedOrigins(Arrays.asList("https://api-gijon11x12.factoriaf5asturias.org/"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}