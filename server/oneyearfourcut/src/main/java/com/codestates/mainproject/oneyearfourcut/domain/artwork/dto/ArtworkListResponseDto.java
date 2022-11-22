package com.codestates.mainproject.oneyearfourcut.domain.artwork.dto;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ArtworkListResponseDto {
    private List<ArtworkResponseDto> artworkListResponse;

    @Builder
    public ArtworkListResponseDto(List<ArtworkResponseDto> artworkListResponse) {
        this.artworkListResponse = artworkListResponse;
    }

    public static ArtworkListResponseDto of(List<Artwork> artworkList) {

        List<ArtworkResponseDto> artworkListResponseDto =
                artworkList.stream().map(Artwork::toArtworkResponseDto)
                        .collect(Collectors.toList());

        return ArtworkListResponseDto.builder()
                .artworkListResponse(artworkListResponseDto)
                .build();

    }
}
