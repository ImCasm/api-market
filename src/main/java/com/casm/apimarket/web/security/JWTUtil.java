package com.casm.apimarket.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private final short MINUTS = 10;
    private static final String KEY = "A8735B3E74A28539D9C18CB279555236B4FA655847490DBD130B313D4881B155";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + MINUTS * 60000))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }
}
