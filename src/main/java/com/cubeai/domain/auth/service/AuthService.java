package com.cubeai.domain.auth.service;

import com.cubeai.domain.auth.dto.TokenResponseDto;
import com.cubeai.global.error.ErrorCode;
import com.cubeai.global.error.exception.UnauthorizedException;
import com.cubeai.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JwtProvider jwtProvider;

    public TokenResponseDto getToken(Long memberId) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(String.valueOf(memberId), null, null);
        String accessToken = jwtProvider.generateAccessToken(authentication);
        String refreshToken = jwtProvider.generateRefreshToken(authentication);
        return new TokenResponseDto(accessToken, refreshToken);
    }

    public void reissueToken(String refreshToken, HttpServletResponse response) {
        if (!StringUtils.hasText(refreshToken)) {
            throw new UnauthorizedException(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
        }
        jwtProvider.validateRefreshToken(refreshToken);
        Authentication authentication = jwtProvider.getAuthentication(refreshToken);
        // TODO: Redis에 저장된 refreshToken과 비교하여 일치하는지 확인
        String newAccessToken = jwtProvider.generateAccessToken(authentication);
        String newRefreshToken = jwtProvider.generateRefreshToken(authentication);
        // TODO: Redis에 새로 발급한 refreshToken 저장
        jwtProvider.writeTokenCookies(response, newAccessToken, newRefreshToken);
    }
}
