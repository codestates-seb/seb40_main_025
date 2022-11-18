package com.codestates.mainproject.oneyearfourcut.domain.gallery.service;

import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.GalleryStatus;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.repository.GalleryRepository;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GalleryServiceMockTest {
    @Mock
    private GalleryRepository galleryRepository;
    @Mock
    private MemberService memberService;
    @InjectMocks
    private GalleryService galleryService;

    Long closedGalleryId1 = 1L;
    Long closedGalleryId2 = 2L;
    Long openGalleryId = 3L;
    Gallery closedGallery1 = Gallery.builder()
            .title("test gallery1")
            .galleryId(closedGalleryId1)
            .content("test content1")
            .status(GalleryStatus.CLOSED)
            .build();
    Gallery closedGallery2 = Gallery.builder()
            .title("test gallery2")
            .galleryId(closedGalleryId2)
            .content("test content2")
            .status(GalleryStatus.CLOSED)
            .build();
    Gallery openGallery = Gallery.builder()
            .title("test gallery3")
            .galleryId(openGalleryId)
            .content("test content3")
            .status(GalleryStatus.OPEN)
            .build();

    @Test
    @DisplayName("폐쇄 갤러리 조회 테스트")
    void findClosedGallery() {
        //given

        given(galleryRepository.findById(closedGalleryId1))
                .willReturn(Optional.ofNullable(closedGallery1));

        //when
        //then
        assertThatThrownBy(() -> galleryService.findGallery(closedGalleryId1))
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage(ExceptionCode.CLOSED_GALLERY.getMessage());
    }
    @Test
    @DisplayName("오픈 갤러리 조회 테스트")
    void findOpenGallery() {
        //given

        given(galleryRepository.findById(openGalleryId))
                .willReturn(Optional.ofNullable(openGallery));

        //when
        //then
        assertThat(galleryService.findGallery(openGalleryId)).isEqualTo(openGallery);
    }
    @Test
    @DisplayName("없는 갤러리 조회 테스트")
    void findNullGallery() {
        //given
        Long nullGalleryId = 100L;
        given(galleryRepository.findById(nullGalleryId))
                .willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> galleryService.findGallery(nullGalleryId))
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage(ExceptionCode.GALLERY_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("갤러리 없는 회원_갤러리 검증 테스트")
    void verifiedGalleryExist1() {
        // given
        Long emptyMemberId = 1L;

        Member emptyMember = Member.builder()
                .memberId(emptyMemberId)
                .email("test1@gmail.com")
                .nickname("nickname1")
                .galleryList(null)
                .build();

        given(memberService.findMember(emptyMemberId))
                .willReturn(emptyMember);

        // when
        // then
        assertThatNoException().isThrownBy(() -> galleryService.verifiedOpenGalleryExist(emptyMemberId));
    }

    @Test
    @DisplayName("폐쇄 갤러리 회원_갤러리 검증 테스트")
    void verifiedGalleryExist2() {
        // given
        Long closedGalleryMemberId = 1L;

        Member closedGalleryMember = Member.builder()
                .memberId(closedGalleryMemberId)
                .email("test2@gmail.com")
                .nickname("nickname2")
                .galleryList(List.of(closedGallery1, closedGallery2))
                .build();

        given(memberService.findMember(closedGalleryMemberId))
                .willReturn(closedGalleryMember);

        // when
        // then
        assertThatNoException().isThrownBy(() -> galleryService.verifiedOpenGalleryExist(closedGalleryMemberId));
    }

    @Test
    @DisplayName("오픈 갤러리 회원_갤러리 검증 테스트")
    void verifiedGalleryExist3() {
        // given
        Long openGalleryMemberId = 1L;

        Member openGalleryMember = Member.builder()
                .memberId(openGalleryMemberId)
                .email("test3@gmail.com")
                .nickname("nickname3")
                .galleryList(List.of(closedGallery1, closedGallery2, openGallery))
                .build();


        given(memberService.findMember(openGalleryMemberId))
                .willReturn(openGalleryMember);

        // when
        // then
        assertThatExceptionOfType(BusinessLogicException.class)
                .isThrownBy(() -> galleryService.verifiedOpenGalleryExist(openGalleryMemberId))
                .withMessage(ExceptionCode.OPEN_GALLERY_EXIST.getMessage());
    }
}