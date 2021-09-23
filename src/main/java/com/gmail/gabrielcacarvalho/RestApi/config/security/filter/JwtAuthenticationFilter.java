package com.gmail.gabrielcacarvalho.RestApi.config.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.gmail.gabrielcacarvalho.RestApi.config.security.dto.AuthenticationResponseDTO;
import com.gmail.gabrielcacarvalho.RestApi.config.security.util.JwtUtil;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.CredencialClienteDTO;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            CredencialClienteDTO credencialClienteDTO = new ObjectMapper()
                    .readValue(request.getInputStream(), CredencialClienteDTO.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credencialClienteDTO.getUsuario(), credencialClienteDTO.getSenha());
            return authenticationManager.authenticate(authToken);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        String token = jwtUtil.generateToken(user, request);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().append(getResponseAuthentication(token));
    }

    private String getResponseAuthentication(String token) throws JsonProcessingException {
        AuthenticationResponseDTO response = new AuthenticationResponseDTO();

        response.setAccessToken(token);
        response.setExpires(jwtUtil.getExpirationDateFromToken(token));
        response.setIssued(Calendar.getInstance());
        response.setExpiresIn(jwtUtil.getExpiration());

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        return mapper.writeValueAsString(response);
    }
}
