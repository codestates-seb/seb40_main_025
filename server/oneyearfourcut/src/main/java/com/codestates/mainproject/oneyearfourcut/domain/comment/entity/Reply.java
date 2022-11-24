package com.codestates.mainproject.oneyearfourcut.domain.comment.entity;

import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.ReplyResDto;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment; // 댓글 id

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 작성자 회원 id

    @Enumerated(EnumType.STRING)
    private CommentStatus replyStatus; //삭제 여부

    @Builder
    public Reply(String content, Comment comment, Member member, CommentStatus replyStatus) {
        this.content = content;
        this.comment = comment;
        this.member = member;
        this.replyStatus = replyStatus;
    }

    public void changeReplyStatus(CommentStatus replyStatus) {
        this.replyStatus = replyStatus;
    }

    public void changeContent(String content) {
        this.content = content;
    }


    public ReplyResDto toReplyResponseDto(Reply reply){
        if(reply == null){return null;}
        return ReplyResDto.builder()
                .replyId(reply.getReplyId())
                .createdAt(reply.getCreatedAt())
                .modifiedAt(reply.getModifiedAt())
                .content(reply.getContent())
                .memberId(reply.getMember().getMemberId())
                .nickname(reply.getMember().getNickname())
                .build();
    }

}
