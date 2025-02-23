package com.example.demo.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.NonFinal;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

public class JwtUtil {
    @NonFinal
    private static final String SIGNER_KEY = "AETqalmPXzX/gbPYod63rxjq+GC0Yy3yz6OjKWCC8I8h6CHP7ZiKzb4BM5GBhQcl";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    private static final SecretKey KEY = Keys.hmacShaKeyFor(SIGNER_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(String lessorEmail) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(lessorEmail)
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
