package com.codestates.mainproject.oneyearfourcut.global.config.auth.dto;

import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {
    private String nickname;
    private String email;
    private String profile;

    public SessionMember(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.profile = member.getProfile();
    }
}
