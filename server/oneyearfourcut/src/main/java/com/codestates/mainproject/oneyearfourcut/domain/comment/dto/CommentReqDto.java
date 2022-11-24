package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;

import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import lombok.*;

@Getter
@AllArgsConstructor
public class CommentReqDto {
    private String content;
    public Comment toComment(CommentReqDto requestDto){
        Comment comment = new Comment();
        comment.setContent(requestDto.getContent());
        return comment;
    }
}
