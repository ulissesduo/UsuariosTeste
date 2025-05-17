package com.Usuarios.config.Security;

import com.Usuarios.Entity.User;
import com.Usuarios.Entity.UserDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String generateToken(UserDTO userDTO) {
        Algorithm algorithm = Algorithm.HMAC256("fiap");

        String token = JWT.create()
                .withIssuer("Usuarios") // nome da aplicação la no application properties primeira linha
                .withSubject(userDTO.email())
                .withExpiresAt(expirationTime())
                .sign(algorithm);
        return token;
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("fiap");
        try{
            return JWT.require(algorithm)
                    .withIssuer("Usuarios").build().verify(token).getSubject();
        }
        catch(JWTVerificationException e) {
            return "";
        }
    }

    //retorna um estante 15 minutos a partir do moomento atual
    public Instant expirationTime(){
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }
}
