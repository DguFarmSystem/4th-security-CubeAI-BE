package com.cubeai.domain.auth.dto;

public record TokenResponseDto(
        String accessToken,
        String refreshToken
) {
}
