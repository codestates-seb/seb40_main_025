package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;


import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArtworkCommentResponseDto extends Auditable {
    private Long commentId;
    private Long memberId;
    private Long artworkId;
    private String nickname;
    private String content;
    private List<ReplyResponseDto> replyList;
    @Builder
    public ArtworkCommentResponseDto(Long commentId, Long memberId, Long artworkId, String nickname, String content, List<ReplyResponseDto> replyList) {
        this.commentId = commentId;
        this.memberId = memberId;
        this.artworkId = artworkId;
        this.nickname = nickname;
        this.content = content;
        this.replyList = replyList;
    }
}
