package br.com.purple.api.config.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationManager getAuthManager(){
           return authenticationManager;
    }

    public Long getExpiration() {
        return expiration;
    }

    public String generateToken(User user){
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    public Date getExpirationDateFromToken(String token) {
       return JWT.decode(token).getExpiresAt();
    }


    public Algorithm getAlgorithm(){
        return Algorithm.HMAC256(secret.getBytes());
    }
}
