package com.codestates.mainproject.oneyearfourcut.domain.member.entity;


import com.codestates.mainproject.oneyearfourcut.domain.alarm.entity.Alarm;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.Like.entity.ArtworkLike;
import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String nickname;

    private String email;

<<<<<<< HEAD
=======
    private String profile;

    @Enumerated(EnumType.STRING)
    private Role role;

>>>>>>> ac779368eac5adbac302f77d8edb1d5321e92a1a
    @OneToMany(mappedBy = "member")
    private List<Gallery> galleryList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ArtworkLike> artworkLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Artwork> artworkList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Alarm> alarmList = new ArrayList<>();

}
