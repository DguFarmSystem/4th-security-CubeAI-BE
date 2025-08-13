package com.cubeai.global.jwt;

import com.cubeai.domain.auth.dto.AuthDto;
import com.cubeai.domain.auth.dto.CustomOAuth2User;
import com.cubeai.global.error.ErrorCode;
import com.cubeai.global.error.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, jwtProperties.getExpiration().getAccess());
    }

    public String generateRefreshToken(Authentication authentication) {
        return generateToken(authentication, jwtProperties.getExpiration().getRefresh());
    }

    public String generateToken(Authentication authentication, long expirationTime) {
        String memberId = authentication.getName();
//        String roleStr = authentication.getAuthorities().stream()
//                .findFirst()
//                .map(GrantedAuthority::getAuthority)
//                .orElseThrow(() -> new UnauthorizedException(ErrorCode.INVALID_AUTHORITY));
        return Jwts.builder()
                .claim("memberId", Long.parseLong(memberId))
//                .claim("role", roleStr)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    public void validateAccessToken(String token) {
        try {
            getJwtParser().parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(ErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (JwtException e) {
            throw new UnauthorizedException(ErrorCode.INVALID_ACCESS_TOKEN_VALUE);
        }
    }

    public void validateRefreshToken(String token) {
        try {
            getJwtParser().parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(ErrorCode.EXPIRED_REFRESH_TOKEN);
        } catch (JwtException e) {
            throw new UnauthorizedException(ErrorCode.INVALID_REFRESH_TOKEN_VALUE);
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getJwtParser().parseSignedClaims(token).getPayload();
        Long memberId = claims.get("memberId", Long.class);
//        String roleStr = claims.get("role", String.class); // "ROLE_ADMIN"
//        Role role = Role.valueOf(roleStr.replace("ROLE_", "")); // enum Role.ADMIN
        AuthDto authDto = AuthDto.from(memberId);
        CustomOAuth2User principal = new CustomOAuth2User(authDto);
        return new UsernamePasswordAuthenticationToken(principal, token, null);
    }

    public void writeTokenCookies(HttpServletResponse response, String accessToken, String refreshToken) {
        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", accessToken)
                .path("/")
                .httpOnly(true)
                .maxAge(jwtProperties.getExpiration().getAccess())
                .domain(jwtProperties.getCookieDomain())
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                .path("/")
                .httpOnly(true)
                .maxAge(jwtProperties.getExpiration().getRefresh())
                .domain(jwtProperties.getCookieDomain())
                .build();

        response.addHeader("Set-Cookie", accessTokenCookie.toString());
        response.addHeader("Set-Cookie", refreshTokenCookie.toString());
    }

    private JwtParser getJwtParser() {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build();
    }
}