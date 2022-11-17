package com.codestates.mainproject.oneyearfourcut.domain.comment.mapper;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper {


    CommentRequestDto commentRequestDtoToComment(Comment comment);

    default ArtworkCommentSingleResponseDto commentToArtworkCommentSingleResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        ArtworkCommentSingleResponseDto artworkCommentSingleResponseDto = new ArtworkCommentSingleResponseDto();

        artworkCommentSingleResponseDto.setCommentId( comment.getCommentId() );
        artworkCommentSingleResponseDto.setContent( comment.getContent() );
        artworkCommentSingleResponseDto.setNickname( comment.getMember().getNickname() );
        artworkCommentSingleResponseDto.setMemberId( comment.getMember().getMemberId() );

        return artworkCommentSingleResponseDto;
    }
    default GalleryCommentSingleResponseDto commentToGalleryCommentSingleResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Artwork artwork = new Artwork();
        GalleryCommentSingleResponseDto gallaryCommentSingleResponseDto = new GalleryCommentSingleResponseDto();

        gallaryCommentSingleResponseDto.setCommentId( comment.getCommentId() );
        gallaryCommentSingleResponseDto.setContent( comment.getContent() );
        gallaryCommentSingleResponseDto.setNickname( comment.getMember().getNickname() );
        gallaryCommentSingleResponseDto.setMemberId( comment.getMember().getMemberId() );
        if ( artwork.getArtworkId() == null ) {
            return null;
        }
        gallaryCommentSingleResponseDto.setArtworkId( artwork.getArtworkId() );

        return gallaryCommentSingleResponseDto;
    }

    ArtworkCommentMultiResponseDto artworkCommentSingleResponseDtoToArtworkCommentMultiResponseDto(
            ArtworkCommentMultiResponseDto artworkCommentMultiResponseDto);
    GalleryCommentMultiResponseDto gallaryCommentSingleResponseDtoToArtworkCommentMultiResponseDto(
            GalleryCommentMultiResponseDto galleryCommentMultiResponseDto);

    default ReplyRequestDto replyRequestDtoToReply(Reply reply) {
        if ( reply == null ) {
            return null;
        }
        ReplyRequestDto replyRequestDto = new ReplyRequestDto();
        replyRequestDto.setContent( reply.getContent() );
        replyRequestDto.setCommentId( reply.getComment().getCommentId());
        return replyRequestDto;
    }
    default ReplyResponseDto replyToReplyResponseDto(Reply reply) {
        if ( reply == null ) {
            return null;
        }
        ReplyResponseDto replyResponseDto = new ReplyResponseDto();

        replyResponseDto.setContent( reply.getContent() );
        replyResponseDto.setCommentId( reply.getComment().getCommentId() );
        replyResponseDto.setNickname( reply.getMember().getNickname() );
        replyResponseDto.setMemberId( reply.getMember().getMemberId() );

        return replyResponseDto;
    }

}

