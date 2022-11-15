package com.codestates.mainproject.oneyearfourcut.domain.member.entity;


import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.vote.entity.Vote;
import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String nickName;

    private String email;

//    private LocalTime lastLoginAT = LocalTime.now(); 확인 후 추가

    @OneToMany(mappedBy = "member")
    private List<Gallery> galleryList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Vote> voteList = new ArrayList<>();
}
