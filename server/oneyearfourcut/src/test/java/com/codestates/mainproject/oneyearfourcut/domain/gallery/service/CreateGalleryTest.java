package com.codestates.mainproject.oneyearfourcut.domain.gallery.service;

import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.GalleryStatus;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.repository.GalleryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CreateGalleryTest {
    @Mock
    private GalleryRepository galleryRepository;
    @InjectMocks
    private GalleryService galleryService;

    @Test
    void 전시_등록이_성공한다() {
        //given
        Long memberId = 1L;
        String title = "승필의 전시회";
        String content = "어서오세요";

        Gallery postGallery = Gallery.builder()
                .title(title)
                .content(content)
                .build();

        //save시 들어온 객체 그대로 반환하도록 mockito적용
        given(galleryRepository.save(postGallery))
                .willReturn(postGallery);

        //when
        Gallery createdGallery = galleryService.createGallery(postGallery, memberId);

        //then
        assertThat(createdGallery.getMember().getMemberId()).isEqualTo(memberId);
        assertThat(createdGallery.getStatus()).isEqualTo(GalleryStatus.OPEN);
        assertThat(createdGallery.getTitle()).isEqualTo(title);
        assertThat(createdGallery.getContent()).isEqualTo(content);
    }
}
