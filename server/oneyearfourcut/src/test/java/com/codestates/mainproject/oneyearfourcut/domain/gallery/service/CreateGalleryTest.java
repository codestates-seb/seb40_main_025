package com.codestates.mainproject.oneyearfourcut.domain.gallery.service;

import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.GalleryStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CreateGalleryTest {
    @InjectMocks
    private GalleryService galleryService;


    @Test
    void 전시_엔티티_생성이_성공한다() {
        //given
        Long memberId = 1L;
        String title = "승필의 전시회";
        String content = "어서오세요";

        Gallery postGallery = Gallery.builder()
                .title(title)
                .content(content)
                .build();

        //when
        Gallery createdGallery = galleryService.createGallery(postGallery, memberId);

        //then
        assertThat(createdGallery.getMember().getMemberId()).isEqualTo(memberId);
        assertThat(createdGallery.getStatus()).isEqualTo(GalleryStatus.OPEN);
        assertThat(createdGallery.getMember().getMemberId()).isEqualTo(memberId);
    }
}
