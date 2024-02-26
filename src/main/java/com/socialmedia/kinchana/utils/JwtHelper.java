package com.socialmedia.kinchana.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.PublicKey;
@Service
public class JwtHelper {
    @Value("${jwt.key.secretKey}")
    private String secretKey;
    private Logger logger = LoggerFactory.getLogger(JwtHelper.class);
    public JwtHelper(){

    }
    public String generateSecretKey(){
        SecretKey secretKey = Jwts.SIG.HS256.key().build();
        String key = Encoders.BASE64.encode(secretKey.getEncoded());
        return key;
    }
    public String generateToken(String message){
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        String token = Jwts.builder()
                .header()
                        .keyId(secretKey)
                        .and()
                .subject(message)
                .signWith(key)
                .compact();
        return token;
    }
    public Claims decodeToken(String token){
        try {
                SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token)
                        .getBody();
                return claims;
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return null;
    }

}
