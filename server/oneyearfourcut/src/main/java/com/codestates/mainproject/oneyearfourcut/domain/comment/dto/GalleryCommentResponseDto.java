package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;


import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GalleryCommentResponseDto extends Auditable {
    private Long galleryId;
    private Long commentId;
    private Long memberId;
    private String nickname;
    private String content;
    private Long artworkId; //it can be NULL
    private List<ReplyResponseDto> replyList;
    @Builder
    public GalleryCommentResponseDto(Long galleryId, Long commentId, Long memberId, String nickname, String content, Long artworkId, List<ReplyResponseDto> replyList) {
        this.galleryId = galleryId;
        this.commentId = commentId;
        this.memberId = memberId;
        this.nickname = nickname;
        this.content = content;
        this.artworkId = artworkId;
        this.replyList = replyList;
    }
}
