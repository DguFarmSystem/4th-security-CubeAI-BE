package com.cubeai.domain.auth.controller;

import com.cubeai.domain.auth.service.AuthService;
import com.cubeai.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "임시 토큰 발급 API",
            description = "사용자 ID를 기반으로 액세스 토큰과 리프레쉬 토큰을 발급합니다."
    )
    @PostMapping("/token")
    public ResponseEntity<SuccessResponse<?>> getToken(@RequestParam Long memberId) {
        return SuccessResponse.ok(authService.getToken(memberId));
    }

    @Operation(
            summary = "토큰 재발급 API",
            description = "리프레쉬 토큰을 사용하여 새로운 액세스 토큰과 리프레쉬 토큰을 쿠키로 발급합니다."
    )
    @PostMapping("/reissue")
    public ResponseEntity<SuccessResponse<?>> reissueToken(
            @Parameter(hidden = true)
            @CookieValue(required = false) String refreshToken, HttpServletResponse response) {
        authService.reissueToken(refreshToken, response);
        return SuccessResponse.noContent();
    }
}
