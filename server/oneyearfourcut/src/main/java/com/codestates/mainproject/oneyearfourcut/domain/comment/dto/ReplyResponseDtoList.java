package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;

import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ReplyResponseDtoList {

    private List<Reply> replyList;

    public List<ReplyResponseDto> ToListDto(List<Reply> replyList){
        return replyList.stream()
                .map(ReplyResponseDto::entityToResponse)
                .collect(Collectors.toList());
    }

}
