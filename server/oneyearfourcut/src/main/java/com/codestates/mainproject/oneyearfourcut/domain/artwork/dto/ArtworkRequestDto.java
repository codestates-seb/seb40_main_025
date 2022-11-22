package com.codestates.mainproject.oneyearfourcut.domain.artwork.dto;


import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ArtworkRequestDto {

    private MultipartFile img;
    private String title;
    private String content;

    public Artwork toEntity() {
        return Artwork.builder()
                .img(img)
                .title(title)
                .content(content)
                .build();
    }
}
