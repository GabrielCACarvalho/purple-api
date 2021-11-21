package br.com.purple.api.config.security;

import br.com.purple.api.config.security.filter.JwtAuthenticationFilter;
import br.com.purple.api.config.security.filter.JwtAuthorizationFilter;
import br.com.purple.api.config.security.util.JwtUtil;
import br.com.purple.api.usecase.CredencialClienteUseCase;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CredencialClienteUseCase credencialClienteUseCase;

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

    //MÉTODOS QUE NÃO PRECISAM DE AUTENTICAÇÃO
    private static final String[] PUBLIC_MATCHES = {
            "/correio/calcula/preco/prazo",
            "/correio/consulta/cep/{cep}"
    };

    //MÉTODOS PARA FAZER AUTENTICAÇÃO
    private static final String[] PUBLIC_MATCHES_AUTH = {
            "/login",
            "/role/**",
            "/credencial/**"
    };

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(credencialClienteUseCase)
                    .passwordEncoder(CredencialClienteUseCase.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(PUBLIC_MATCHER_SWAGGER).permitAll();
        http.authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll();
        http.authorizeRequests().antMatchers(PUBLIC_MATCHES_AUTH).permitAll();
        //http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().anyRequest().permitAll();

        http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
