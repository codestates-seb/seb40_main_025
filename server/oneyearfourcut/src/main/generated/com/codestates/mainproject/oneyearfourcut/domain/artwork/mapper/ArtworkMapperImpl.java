package com.codestates.mainproject.oneyearfourcut.domain.artwork.mapper;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.dto.ArtworkRequestDto;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.dto.ArtworkResponseDto;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-25T14:40:10+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16 (Azul Systems, Inc.)"
)
@Component
public class ArtworkMapperImpl implements ArtworkMapper {

    @Override
    public Artwork artworkRequestDtoToArtwork(ArtworkRequestDto artworkRequestDto) {
        if ( artworkRequestDto == null ) {
            return null;
        }

        Artwork artwork = new Artwork();

        artwork.setTitle( artworkRequestDto.getTitle() );
        artwork.setContent( artworkRequestDto.getContent() );
        artwork.setImg( artworkRequestDto.getImg() );

        return artwork;
    }

    @Override
    public List<ArtworkResponseDto> artworkListToArtworkListResponseDto(List<Artwork> artworkList) {
        if ( artworkList == null ) {
            return null;
        }

        List<ArtworkResponseDto> list = new ArrayList<ArtworkResponseDto>( artworkList.size() );
        for ( Artwork artwork : artworkList ) {
            list.add( artworkToArtworkResponseDto( artwork ) );
        }

        return list;
    }

    @Override
    public ArtworkResponseDto artworkToArtworkResponseDto(Artwork artwork) {
        if ( artwork == null ) {
            return null;
        }

        ArtworkResponseDto.ArtworkResponseDtoBuilder artworkResponseDto = ArtworkResponseDto.builder();

        if ( artwork.getArtworkId() != null ) {
            artworkResponseDto.artworkId( artwork.getArtworkId() );
        }
        if ( artwork.getMemberId() != null ) {
            artworkResponseDto.memberId( artwork.getMemberId() );
        }
        artworkResponseDto.title( artwork.getTitle() );
        artworkResponseDto.content( artwork.getContent() );
        artworkResponseDto.imagePath( artwork.getImagePath() );
        artworkResponseDto.likeCount( artwork.getLikeCount() );
        artworkResponseDto.commentCount( artwork.getCommentCount() );

        return artworkResponseDto.build();
    }
}
