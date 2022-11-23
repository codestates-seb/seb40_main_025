package com.codestates.mainproject.oneyearfourcut.domain.comment.mapper;


import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);


    Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto);

    default GalleryCommentResponse commentToGalleryCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        GalleryCommentResponse galleryCommentResponse = new GalleryCommentResponse();

        galleryCommentResponse.setCommentId( comment.getCommentId() );
        galleryCommentResponse.setCreatedAt( comment.getCreatedAt() );
        galleryCommentResponse.setModifiedAt( comment.getModifiedAt() );
        galleryCommentResponse.setContent( comment.getContent() );
        galleryCommentResponse.setArtworkId( comment.getArtworkId() );
        galleryCommentResponse.setMemberId(comment.getMember().getMemberId());
        galleryCommentResponse.setNickname(comment.getMember().getNickname());
        /*galleryCommentResponse.setGalleryId(comment.getGallery().getGalleryId());*/


        return galleryCommentResponse;
    }

    List<GalleryCommentResponse> commentToGalleryCommentResponseList(List<Comment> commentList);

    default ArtworkCommentResponse commentToArtworkCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        ArtworkCommentResponse artworkCommentResponse = new ArtworkCommentResponse();

        artworkCommentResponse.setCommentId( comment.getCommentId() );
        artworkCommentResponse.setCreatedAt( comment.getCreatedAt() );
        artworkCommentResponse.setModifiedAt( comment.getModifiedAt() );
        artworkCommentResponse.setContent( comment.getContent() );
        /*artworkCommentResponse.setArtworkId( comment.getArtworkId() );*/
        artworkCommentResponse.setMemberId(comment.getMember().getMemberId());
        artworkCommentResponse.setNickname(comment.getMember().getNickname());


        return artworkCommentResponse;
    }

    List<ArtworkCommentResponse> commentToArtworkCommentResponseList(List<Comment> commentList);


}










