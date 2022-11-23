package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;

import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReplyResponseDto extends Auditable {
    public Long replyId;
    private Long memberId;
    private String nickname;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public ReplyResponseDto(Long replyId, Long memberId, String nickname, String content) {
        this.replyId = replyId;
        this.memberId = memberId;
        this.nickname = nickname;
        this.content = content;
    }

    public static ReplyResponseDto entityToResponse(Reply reply){
        return reply.getReplyStatus() == CommentStatus.DELETED ?
                new ReplyResponseDto(
                        reply.getReplyId(),
                        null,
                        null,
                        "삭제된 대댓글입니다.") :
                new ReplyResponseDto(
                        reply.getReplyId(),
                        reply.getMember().getMemberId(),
                        reply.getMember().getNickname(),
                        reply.getContent());
    }


}
