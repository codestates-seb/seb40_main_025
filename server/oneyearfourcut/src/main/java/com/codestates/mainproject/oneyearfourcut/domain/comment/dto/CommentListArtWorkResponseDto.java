package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;

import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentListArtWorkResponseDto {
    private Long artWorkId;
    private List<CommentArtWorkResponseDto> commentList;
}
