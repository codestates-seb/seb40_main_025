package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtworkCommentMultiResponseDto {
    private Long artworkId;
    private List<ArtworkCommentSingleResponseDto> commentList;
    private List<ReplyResponseDto> replyList;
}
