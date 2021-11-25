package br.com.purple.api.config.security;

import br.com.purple.api.config.security.filter.JwtAuthorizationFilter;
import br.com.purple.api.usecase.CredencialUseCase;
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

    private final CredencialUseCase credencialUseCase;

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

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
            auth.userDetailsService(credencialUseCase)
                    .passwordEncoder(CredencialUseCase.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHER_SWAGGER).permitAll()
                .antMatchers(PUBLIC_MATCHES).permitAll()
                .antMatchers(PUBLIC_MATCHES_AUTH).permitAll()
                .anyRequest().permitAll()
                //.anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
