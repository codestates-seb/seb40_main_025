package com.codestates.mainproject.oneyearfourcut.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberRequestDto { //회원 수정용 requstDto
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9 ]{1,8}$", message = "닉네임은 특수문자를 제외한 최소 1 이상 8 이하까지 작성할 수 있습니다.")
    private String nickname;
    private MultipartFile profile;

    @Builder    //테스트 코드용 생성자
    public MemberRequestDto(String nickname, MultipartFile profile) {
        this.nickname = nickname;
        this.profile = profile;
    }
}
