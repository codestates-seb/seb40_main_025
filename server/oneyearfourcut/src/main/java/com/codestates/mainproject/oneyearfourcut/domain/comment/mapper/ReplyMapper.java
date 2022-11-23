package com.codestates.mainproject.oneyearfourcut.domain.comment.mapper;


import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.GalleryCommentResponse;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.ReplyResponseDto;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReplyMapper {


    default ReplyResponseDto replyToReplyResponseDto(Reply reply) {
        if ( reply == null ) {
            return null;
        }

        ReplyResponseDto replyResponseDto = new ReplyResponseDto();

        replyResponseDto.setReplyId( reply.getReplyId() );
        replyResponseDto.setCreatedAt( reply.getCreatedAt() );
        replyResponseDto.setModifiedAt( reply.getModifiedAt() );
        replyResponseDto.setContent( reply.getContent() );
        replyResponseDto.setMemberId(reply.getMember().getMemberId());
        replyResponseDto.setNickname(reply.getMember().getNickname());


        return replyResponseDto;
    }


    List<ReplyResponseDto> replyToReplyResponseDtoList(List<Reply> replyList);
}
