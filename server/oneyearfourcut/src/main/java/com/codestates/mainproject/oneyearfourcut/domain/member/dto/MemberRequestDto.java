package com.codestates.mainproject.oneyearfourcut.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class MemberRequestDto {    //Oauth2.0 카카오 회원가입으로 변경시 없어질 듯
    private String nickname;
    private MultipartFile profile;

    @Builder    //테스트코드용 생성자
    public MemberRequestDto(String nickname, MultipartFile profile) {
        this.nickname = nickname;
        this.profile = profile;
    }
}
