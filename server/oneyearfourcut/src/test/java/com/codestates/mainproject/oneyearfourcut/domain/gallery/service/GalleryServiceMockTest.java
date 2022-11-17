package com.codestates.mainproject.oneyearfourcut.domain.gallery.service;

import com.codestates.mainproject.oneyearfourcut.domain.gallery.repository.GalleryRepository;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.mapper.MemberMapper;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GalleryServiceMockTest {
    @Mock
    private GalleryRepository galleryRepository;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private GalleryService galleryService;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void findGallery() {

    }

    @Test
    void verifiedGalleryExist() {
        // given
        Long memberId = 1L;
        Member member = new Member();
        member.setMemberId(1L);
        member.setNickname("nickname1");
        member.setEmail("test1@gmail.com");

        given(memberService.findMember(memberId))
                .willReturn(member);


        // when


        // then



    }

    @Test
    void createGallery() {
    }
}