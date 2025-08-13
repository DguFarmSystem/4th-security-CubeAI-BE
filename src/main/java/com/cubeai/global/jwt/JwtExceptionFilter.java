package com.cubeai.global.jwt;

import com.cubeai.global.error.ErrorCode;
import com.cubeai.global.error.ErrorResponse;
import com.cubeai.global.error.exception.InvalidValueException;
import com.cubeai.global.error.exception.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UnauthorizedException e) {
            handleUnauthorizedException(response, e);
        } catch (Exception ee) {
            handleException(response);
        }
    }

    private void handleUnauthorizedException(HttpServletResponse response, Exception e) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        if (e instanceof UnauthorizedException ue) {
            response.setStatus(ue.getErrorCode().getHttpStatus().value());
            response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(ue.getErrorCode())));
        } else if (e instanceof InvalidValueException ie) {
            response.setStatus(ie.getErrorCode().getHttpStatus().value());
            response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(ie.getErrorCode())));
        }
    }

    private void handleException(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR)));
    }
}
