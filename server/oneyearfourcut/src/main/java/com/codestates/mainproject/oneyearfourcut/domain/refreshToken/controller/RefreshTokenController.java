package com.codestates.mainproject.oneyearfourcut.domain.refreshToken.controller;

import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.refreshToken.entity.RefreshToken;
import com.codestates.mainproject.oneyearfourcut.domain.refreshToken.service.RefreshTokenService;
import com.codestates.mainproject.oneyearfourcut.global.config.auth.LoginMember;
import com.codestates.mainproject.oneyearfourcut.global.config.auth.jwt.JwtTokenizer;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/auth/refresh")
    public ResponseEntity refreshAccessToken(HttpServletResponse response, HttpServletRequest request) {
        // 검증하고 재발급 하는 로직?
        String accessToken = Optional.ofNullable(request.getHeader("Authorization"))
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WRONG_ACCESS_TOKEN)).replace("Bearer ", "");
        String refreshToken = Optional.ofNullable(request.getHeader("refresh"))
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WRONG_REFRESH_TOKEN));
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        //만료된 토큰인지 확인
        boolean expiredToken = jwtTokenizer.isExpiredToken(accessToken, base64EncodedSecretKey);

        if (expiredToken) {
            //리프레시 토큰으로 가지고있는 토큰과 비교
            String email = jwtTokenizer.getEmail(refreshToken, jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey()));
            RefreshToken refreshTokenByEmail = refreshTokenService.findRefreshTokenByEmail(email);

            if (refreshTokenByEmail.getToken().equals(refreshToken)) {
                //검증에 통과한다면 새로운 access, refresh token 발행
                Member member = refreshTokenByEmail.getMember();

                Map<String, Object> claims = new HashMap<>();
                String subject = member.getEmail();
                claims.put("username", subject);
                claims.put("roles", List.of("USER"));   //모든 유저 역할 통일
                claims.put("id", member.getMemberId());
                Date accessExpiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
                Date refreshExpiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());

                //토큰들 생성
                String newAccessToken = jwtTokenizer.generateAccessToken(claims, subject, accessExpiration, base64EncodedSecretKey);
                String newRefreshToken = jwtTokenizer.generateRefreshToken(subject, refreshExpiration, base64EncodedSecretKey);

                //리프레시 토큰 다시 저장
                refreshTokenService.updateRefreshToken(member, newRefreshToken);

                response.setHeader("Authorization", "Bearer " + newAccessToken);
                response.setHeader("refresh", newRefreshToken);

                //결과 리턴
                return new ResponseEntity("새로 토큰이 발급되었습니다.", HttpStatus.CREATED);
            }
            //access token은 만료되었고, 준 리프레시 토큰이 유효하지만, 해당 이메일 조회해보니 안에있는 토큰은 다르다
        }

        //여기로 오면 만료되지 않은 access 토큰임 -> 만료되지 않은 토큰인데 왜 여기로 요청했지?
        return new ResponseEntity<>("만료되지 않은 access token 이거나 잘못된 refresh token 입니다.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/auth/logout")
    public ResponseEntity logout(@LoginMember Long memberId) {
        refreshTokenService.deleteRefreshToken(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
