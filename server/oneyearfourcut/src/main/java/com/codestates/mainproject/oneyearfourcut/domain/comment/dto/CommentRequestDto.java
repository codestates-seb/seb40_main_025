package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;

import lombok.*;

@Getter
@Setter
public class CommentRequestDto{
    private String content;
    private Long galleryId;
    private Long artWorkId;
}
