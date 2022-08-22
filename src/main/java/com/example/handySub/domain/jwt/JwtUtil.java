//토큰 생성
//토큰으로부터 정보 추출(해석)
//토큰의 유효성 검사
package com.example.handySub.domain.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public final class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    //암호키(secret key)
    private static final String secretkey = "handysub-secretkey";
    //토큰 유효시간(7일) - 일단은...
    private static final int jwtExpirationMs = 604800000;

    //jwt token 생성
    public static String generateToken(Authentication authentication){
        Date now = new Date();
        Date expireDate = new Date(now.getTime()+jwtExpirationMs);

        return Jwts.builder()
                .setSubject((String)authentication.getPrincipal()) //사용자
                .setIssuedAt(new Date()) //현재 시간으로 생성
                .setExpiration(expireDate) //만료 시간 set
                .signWith(SignatureAlgorithm.HS512, secretkey) //암호화 알고리즘, signature의 secret 값
                .compact();
    }
    //token으로부터 정보 추출
    public static String getUserInfoFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretkey)
                .parseClaimsJwt(token)
                .getBody();

        return claims.getSubject();
    }
    //token의 유효성 검사
    public static Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
