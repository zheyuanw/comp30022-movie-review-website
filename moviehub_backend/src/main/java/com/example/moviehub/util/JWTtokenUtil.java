package com.example.moviehub.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.moviehub.collection.User;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class JWTtokenUtil {

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

    public static Boolean validateToken(String token){
        try{
            Long expiresAt = JWT.decode(token).getExpiresAt().getTime();
            Calendar cal = Calendar.getInstance();
            if (expiresAt >= cal.getTime().getTime()){
                return true;
            }


        }catch (IllegalArgumentException e){
            System.out.println(String.format("JWT is invalid - {%s}", e.getMessage()));
        }
        return false;
    }

    public static String getUserEmail(String token){
        String subject = JWT.decode(token).getSubject();

        System.out.println("token_subject:" +  subject);
        return  subject;
    }
}
