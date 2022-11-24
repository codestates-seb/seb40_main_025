package com.codestates.mainproject.oneyearfourcut.domain.comment.dto;


import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentGalleryResDto extends Auditable {
    private Long commentId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long memberId;
    private String nickname;
    private String content;
    private Long artworkId; //NULL possible

    public static List<CommentGalleryResDto> toGalleryResponseDtoList(List<Comment> commentList){
        List<CommentGalleryResDto> resultList = new ArrayList<>( commentList.size() );
        for ( Comment comment : commentList ) {
            resultList.add( comment.toGalleryResponseDto( comment ) );
        }
        return resultList;
    }
}