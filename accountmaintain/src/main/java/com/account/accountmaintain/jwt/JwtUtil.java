package com.account.accountmaintain.jwt;


import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;
    
    
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }


    public String extractUsername(String token) {
    	return Jwts.parser()
                .setSigningKey(getSigningKey())  // Updated method
                .build()
                .parseSignedClaims(token)  // Updated method
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return Jwts.parser()
        		.setSigningKey(getSigningKey())  // Updated method
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
}

