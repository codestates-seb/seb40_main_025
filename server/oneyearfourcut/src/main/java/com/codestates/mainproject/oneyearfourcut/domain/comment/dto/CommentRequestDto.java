package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;

import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
public class CommentRequestDto{
    private Long memberId;
    private String content;
}
