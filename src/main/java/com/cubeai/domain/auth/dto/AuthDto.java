package com.cubeai.domain.auth.dto;

import lombok.Builder;

@Builder
public record AuthDto(
        Long memberId,
        String oAuthId
) {
    public static AuthDto from(Long memberId) {
        return AuthDto.builder()
                .memberId(memberId)
                .build();
    }

    public static AuthDto of(Long memberId, String oAuthId) {
        return AuthDto.builder()
                .memberId(memberId)
                .oAuthId(oAuthId)
                .build();
    }
}
