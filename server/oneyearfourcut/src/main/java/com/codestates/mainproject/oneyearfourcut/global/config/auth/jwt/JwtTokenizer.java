package com.codestates.mainproject.oneyearfourcut.global.config.auth.jwt;

import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenizer {
    @Getter
    @Value("${jwt.key.secret}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // 검증 후, Claims을 반환 하는 용도
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }


    // 단순히 검증만 하는 용도로 쓰일 경우
    public boolean isExpiredToken(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jws);

        } catch (ExpiredJwtException e) {
            return true;
        } catch (SignatureException e) {    //유효하지 않은 토큰은 에러 던짐
            throw new BusinessLogicException(ExceptionCode.WRONG_ACCESS_TOKEN);
        }

        return false;
    }

    public String getEmail(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        String email = null;
        try {
            email = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jws)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new BusinessLogicException(ExceptionCode.EXPIRED_REFRESH_TOKEN);
        } catch (SignatureException e) {
            throw new BusinessLogicException(ExceptionCode.WRONG_REFRESH_TOKEN);
        } catch (Exception e) {
            throw new RuntimeException("토큰에서 이메일을 가져오지 못했습니다.");
        }

        return email;
    }

    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

    //test용 jwt만드는 메서드
    public String testJwtGenerator(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("role", member.getRole());
        claims.put("id", member.getMemberId());

        String subject = String.valueOf(member.getEmail());
        Date expiration = getTokenExpiration(getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = encodeBase64SecretKey(getSecretKey());

        String accessToken = generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return "Bearer " + accessToken;
    }
    public String testRefreshGenerator(Member member) {
        String subject = String.valueOf(member.getEmail());
        Date expiration = getTokenExpiration(getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = encodeBase64SecretKey(getSecretKey());

        return generateRefreshToken(subject, expiration, base64EncodedSecretKey);
    }
}
