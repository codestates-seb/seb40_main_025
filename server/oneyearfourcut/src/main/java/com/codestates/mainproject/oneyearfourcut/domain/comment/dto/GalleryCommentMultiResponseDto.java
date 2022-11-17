package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GalleryCommentMultiResponseDto {
    private Long galleryId;
    private List<GalleryCommentSingleResponseDto> commentList;
    private List<ReplyResponseDto> replyList;
}
