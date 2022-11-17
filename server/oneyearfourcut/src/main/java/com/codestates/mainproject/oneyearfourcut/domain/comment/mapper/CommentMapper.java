package com.codestates.mainproject.oneyearfourcut.domain.comment.mapper;

import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    List<ArtworkCommentResponseDto> commentToArtworkCommentResponseDtoList(List<Comment> commentList);

    List<GalleryCommentResponseDto> commentToGalleryCommentResponseDtoList(List<Comment> commentList);

    default Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto){
        if(commentRequestDto == null){
            return null;
        }
        Member member = new Member();
        member.setMemberId(commentRequestDto.getMemberId());

        Comment.CommentBuilder comment = Comment.builder();
        comment.member(member);
        comment.content(commentRequestDto.getContent());

        return comment.build();
    }

    default ArtworkCommentResponseDto commentToArtworkCommentResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        ArtworkCommentResponseDto.ArtworkCommentResponseDtoBuilder artworkCommentResponseDto
                = ArtworkCommentResponseDto.builder();
        artworkCommentResponseDto.artworkId(comment.getArtwork().getArtworkId());
        artworkCommentResponseDto.commentId(comment.getCommentId());
        artworkCommentResponseDto.content( comment.getContent() );
        artworkCommentResponseDto.memberId(comment.getMember().getMemberId());
        artworkCommentResponseDto.nickname(comment.getMember().getNickname());

        return artworkCommentResponseDto.build();
    }



}





    // no.1 mapper //test pass
/*    default Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto) {
        if ( commentRequestDto == null ) {
            return null;
        }
        Member member = new Member();
        member.setMemberId(commentRequestDto.getMemberId());
        Comment.CommentBuilder commentBuilder = Comment.builder();
            commentBuilder.member(member);
            commentBuilder.content(commentRequestDto.getContent() );

        return commentBuilder.build();
    }

 List<GalleryCommentResponseDto> commentToGalleryCommentSingleResponseDtoList(List<Comment> commentList);


    // no.2 mapper
    default ArtworkCommentResponseDto commentToArtworkCommentResponseDto(
            Comment comment) {
        if ( comment == null ) {
            return null;
        }
        ArtworkCommentResponseDto.ArtworkCommentResponseDtoBuilder aCSResponseDto =
                ArtworkCommentResponseDto.builder();
                aCSResponseDto.memberId(comment.getMember().getMemberId());
                aCSResponseDto.nickname(comment.getMember().getNickname());
                aCSResponseDto.commentId(comment.getCommentId());
                aCSResponseDto.content(comment.getContent());

                return aCSResponseDto.build();
    }
}*/




/*    // no.3 mapper
    default GalleryCommentResponseDto commentToGalleryCommentSingleResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Artwork artwork = new Artwork();
        GalleryCommentResponseDto gallaryCommentSingleResponseDto = new GalleryCommentResponseDto();

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
            ArtworkCommentMultiResponseDto artworkCommentSingleResponseDto);

    // no.5 mapper
    GalleryCommentMultiResponseDto gallaryCommentSingleResponseDtoToArtworkCommentMultiResponseDto(
            GalleryCommentMultiResponseDto gallaryCommentSingleResponseDto);

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
    }*/



