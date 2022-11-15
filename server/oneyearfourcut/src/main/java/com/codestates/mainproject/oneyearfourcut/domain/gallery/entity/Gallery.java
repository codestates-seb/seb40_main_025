package com.codestates.mainproject.oneyearfourcut.domain.gallery.entity;

import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Gallery extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galleryId;
    private String title;
    private String content;
    private GalleryStatus status = GalleryStatus.OPEN;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
