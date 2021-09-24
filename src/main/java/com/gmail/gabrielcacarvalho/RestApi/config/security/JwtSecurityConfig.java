package com.gmail.gabrielcacarvalho.RestApi.config.security;

import com.gmail.gabrielcacarvalho.RestApi.config.security.filter.JwtAuthenticationFilter;
import com.gmail.gabrielcacarvalho.RestApi.config.security.filter.JwtAuthorizationFilter;
import com.gmail.gabrielcacarvalho.RestApi.config.security.handler.JwtAuthenticationHandler;
import com.gmail.gabrielcacarvalho.RestApi.config.security.util.JwtUtil;
import com.gmail.gabrielcacarvalho.RestApi.service.CredencialClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CredencialClienteService credencialClienteService;

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    private final JwtUtil jwtUtil;

    //MÉTODOS DO SWAGGER QUE NÃO PRECISAM DE AUTENTICAÇÃO
    private static final String[] PUBLIC_MATCHER_SWAGGER = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/**",
            "/swagger-ui.html",
            "/webjars/**"
    };

    //MÉTODOS PARA FAZER AUTENTICAÇÃO
    private static final String[] PUBLIC_MATCHES_AUTH = {
            "/login",
            "/role/**",
            "/credencial/**"
    };

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(credencialClienteService)
                    .passwordEncoder(credencialClienteService.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(PUBLIC_MATCHER_SWAGGER).permitAll();
        http.authorizeRequests().antMatchers(PUBLIC_MATCHES_AUTH).permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
