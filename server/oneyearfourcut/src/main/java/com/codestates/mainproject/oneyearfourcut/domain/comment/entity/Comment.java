package com.codestates.mainproject.oneyearfourcut.domain.comment.entity;


import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.ArtWork;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId; // 작성자 회원 id

    @ManyToOne
    @JoinColumn(name = "galley_id")
    private Gallery galleryId;  //작품이 아닌 전시관 전체 댓글일때

    @ManyToOne
    @JoinColumn(name = "artwork_id")
    private ArtWork artWorkId;  //작품에 달린 댓글일때.

    @Builder
    public Comment(Long commentId, String content, Member memberId, Gallery galleryId, ArtWork artWorkId){
        this.commentId = commentId;
        this.content = content;
        this.memberId = memberId;
        this.galleryId = galleryId;
        this.artWorkId = artWorkId;
    }


}
