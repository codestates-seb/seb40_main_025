

package com.codestates.mainproject.oneyearfourcut.domain.comment.entity;

import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 작성자 회원 id

    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;  //작품이 아닌 전시관 전체 댓글일때

    @Column
    private Long artworkId;  //작품에 달린 댓글일때. 비식별관계

    @OneToMany(mappedBy = "comment")
    public List<Reply> replyList = new ArrayList<>(); // 대댓글, targetEntity

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;

    @Builder
    public Comment(Long commentId, String content, Member member, Gallery gallery, Long artworkId, List<Reply> replyList, CommentStatus commentStatus) {
        this.commentId = commentId;
        this.content = content;
        this.member = member;
        this.gallery = gallery;
        this.artworkId = artworkId;
        this.replyList = replyList;
        this.commentStatus = commentStatus;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public static Comment setComment(Gallery gallery, Long artworkId, Member member, String content, CommentStatus commentStatus){
        Comment comment = new Comment();
        comment.gallery = gallery;
        comment.artworkId = artworkId;
        comment.member = member;
        comment.content = content;
        comment.commentStatus = CommentStatus.VALID;
        return comment;
    }

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }
}
