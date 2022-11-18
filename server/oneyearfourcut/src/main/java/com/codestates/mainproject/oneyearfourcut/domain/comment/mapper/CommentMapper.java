package com.codestates.mainproject.oneyearfourcut.domain.comment.mapper;

import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

//https://meetup.toast.com/posts/213
//https://www.youtube.com/watch?v=6n4iL5E-Rwo&list=PLF5X0J2bZ_k42wt16-EWfOa2QEGFfPmMS&index=7
//https://stackoverflow.com/questions/59333845/mapstruct-many-to-one-mapping
//https://mapstruct.org/documentation/stable/reference/html/
@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    default Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto) {
        if ( commentRequestDto == null ) {
            return null;
        }
        Member member = new Member();
        member.setMemberId(commentRequestDto.getMemberId());

        Comment.CommentBuilder comment = Comment.builder();
        comment.member(member);

        comment.content( commentRequestDto.getContent() );

        return comment.build();
    }
    

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
        galleryCommentResponse.setGalleryId(comment.getGallery().getGalleryId());
        galleryCommentResponse.setReplyList( null );

        return galleryCommentResponse;
    }



    List<GalleryCommentResponse> commentToGalleryCommentResponseList(List<Comment> commentList);



}








/*default ArtworkCommentResponse commentToArtworkCommentResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        ArtworkCommentResponse.Dto.Builder artworkCommentResponseDto
                = ArtworkCommentResponseDto.Dto.builder();
        *//*artworkCommentResponseDto.artworkId(comment.getArtwork().getArtworkId());*//*
        artworkCommentResponseDto.commentId(comment.getCommentId());
        artworkCommentResponseDto.content( comment.getContent() );
        artworkCommentResponseDto.memberId(comment.getMember().getMemberId());
        artworkCommentResponseDto.nickname(comment.getMember().getNickname());

        return artworkCommentResponseDto.build();
    }
    default GalleryCommentResponseDto commentToGalleryCommentResponseDto(Comment comment){
        if ( comment == null ) {
            return null;
        }
        GalleryCommentResponseDto.GalleryCommentResponseDtoBuilder galleryCommentResponseDto
                = GalleryCommentResponseDto.builder();
        *//*if(comment.getArtwork() == null){
            galleryCommentResponseDto.artworkId(null);
        } //오류가능성있음...!!
        galleryCommentResponseDto.artworkId(comment.getArtwork().getArtworkId());*//*
        galleryCommentResponseDto.galleryId(comment.getGallery().getGalleryId());
        galleryCommentResponseDto.commentId(comment.getCommentId());
        galleryCommentResponseDto.content( comment.getContent() );
        galleryCommentResponseDto.memberId(comment.getMember().getMemberId());
        galleryCommentResponseDto.nickname(comment.getMember().getNickname());

        return galleryCommentResponseDto.build();

    }*/





