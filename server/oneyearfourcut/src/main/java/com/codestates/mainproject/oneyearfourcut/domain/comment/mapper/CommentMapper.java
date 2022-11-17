package com.codestates.mainproject.oneyearfourcut.domain.comment.mapper;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // no.1 mapper
    CommentRequestDto commentRequestDtoToComment(Comment comment);

    // no.2 mapper
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

    // no.3 mapper
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
            gallaryCommentSingleResponseDto.setArtworkId(null);
        }
        gallaryCommentSingleResponseDto.setArtworkId( comment.getArtworkId() );

        return gallaryCommentSingleResponseDto;
    }

    // no.4 mapper
    ArtworkCommentMultiResponseDto artworkCommentSingleResponseDtoToArtworkCommentMultiResponseDto(
            ArtworkCommentMultiResponseDto artworkCommentMultiResponseDto);

    // no.5 mapper
    GalleryCommentMultiResponseDto gallaryCommentSingleResponseDtoToArtworkCommentMultiResponseDto(
            GalleryCommentMultiResponseDto galleryCommentMultiResponseDto);

    // no.6 mapper
    default ReplyRequestDto replyRequestDtoToReply(Reply reply) {
        if ( reply == null ) {
            return null;
        }
        ReplyRequestDto replyRequestDto = new ReplyRequestDto();
        replyRequestDto.setContent( reply.getContent() );
        replyRequestDto.setCommentId( reply.getComment().getCommentId());
        return replyRequestDto;
    }

    // no.7 mapper
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

