package com.example.moviehub.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.moviehub.collection.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTutil {

    private static final String issuer = "MovieHub";

    private static final String secret = "secretKey";


    public static Map<String, String> generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        return tokens;
    }
}
