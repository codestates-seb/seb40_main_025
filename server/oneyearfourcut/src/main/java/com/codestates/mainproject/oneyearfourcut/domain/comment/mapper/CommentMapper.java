package com.codestates.mainproject.oneyearfourcut.domain.comment.mapper;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    default GalleryCommentListResponseDto toGalleryCommentListResponseDto(Comment comment){
        if ( comment == null ) {
            return null;
        }
        Member member = new Member();
        Gallery gallery = new Gallery();
        GalleryCommentResponseDto galleryCommentResponseDto = new GalleryCommentResponseDto();
        GalleryCommentListResponseDto galleryCommentListResponseDto = new GalleryCommentListResponseDto();

        galleryCommentResponseDto.setCommentId( comment.getCommentId() );
        galleryCommentResponseDto.setContent( comment.getContent() );
        galleryCommentResponseDto.setArtworkId( comment.getArtworkId() );
        galleryCommentResponseDto.setNickname( member.getNickname() );
        galleryCommentResponseDto.setMemberId( member.getMemberId() );

        galleryCommentListResponseDto.setGalleryId( gallery.getGalleryId());

        return galleryCommentListResponseDto;
    }

    default ArtworkCommentListResponseDto toArtworkCommentListResponseDto(Comment comment){
        if ( comment == null ) {
            return null;
        }
        Member member = new Member();
        Artwork artwork = new Artwork();
        ArtworkCommentListResponseDto artworkCommentListResponseDto = new ArtworkCommentListResponseDto();
        ArtworkCommentResponseDto artworkCommentResponseDto = new ArtworkCommentResponseDto();

        artworkCommentResponseDto.setCommentId( comment.getCommentId() );
        artworkCommentResponseDto.setContent( comment.getContent() );
        artworkCommentResponseDto.setNickname( member.getNickname() );
        artworkCommentResponseDto.setMemberId( member.getMemberId() );

        artworkCommentListResponseDto.setArtworkId( artwork.getArtworkId());

        return artworkCommentListResponseDto;
    }

    default Comment toComment(CommentRequestDto commentRequestDto) {
        if ( commentRequestDto == null ) {
            return null;
        }
        Comment comment = new Comment();
        comment.setContent( commentRequestDto.getContent() );

        return comment;
    }





}
