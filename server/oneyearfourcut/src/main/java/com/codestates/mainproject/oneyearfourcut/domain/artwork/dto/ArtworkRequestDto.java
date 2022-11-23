package com.codestates.mainproject.oneyearfourcut.domain.artwork.dto;


import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;

@Setter
@Getter
public class ArtworkRequestDto {

    @Lob
    private MultipartFile img;
    private String title;
    private String content;

    @Builder
    public ArtworkRequestDto(MultipartFile img, String title, String content) {
        this.img = img;
        this.title = title;
        this.content = content;
    }

    public Artwork toEntity() {
        return Artwork.builder()
                .image(img)
                .title(title)
                .content(content)
                .build();
    }
}
