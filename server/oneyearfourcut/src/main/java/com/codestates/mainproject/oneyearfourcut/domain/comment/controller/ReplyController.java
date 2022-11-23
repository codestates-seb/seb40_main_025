package com.codestates.mainproject.oneyearfourcut.domain.comment.controller;

import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.CommentRequestDto;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.ReplyResponseDto;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.ReplyResponseDtoList;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import com.codestates.mainproject.oneyearfourcut.domain.comment.mapper.ReplyMapper;
import com.codestates.mainproject.oneyearfourcut.domain.comment.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/galleries")
@Validated
@Slf4j
@AllArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyMapper mapper;

    @PostMapping("/comments/{comment-id}/replies")
    public ResponseEntity<Object> postReplyOnGallery(@PathVariable("comment-id") Long commentId,
                                                       @RequestBody CommentRequestDto replyRequestDto) {
        replyService.createReply(replyRequestDto, commentId, 3L);
        String response = "댓글등록성공";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/comments/{comment-id}/replies")
    public ResponseEntity<Object> getReplyOnGallery(@PathVariable("comment-id") Long commentId) {

        List<Reply> replyList = replyService.findReplyList(commentId, 3L);
        List<ReplyResponseDto> response = mapper.replyToReplyResponseDtoList(replyList);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/comments/comments/replies/{reply-id}")
    public ResponseEntity<Object> patchReply(@PathVariable("reply-id") Long replyId,
                                               @RequestBody CommentRequestDto commentRequestDto){

        replyService.modifyComment(replyId);
        String response = "댓글수정완료!!";
        return new ResponseEntity<>(response, (HttpStatus.OK));
    }

    @DeleteMapping("/comments/comments/replies/{reply-id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("gallery-id") Long replyId,
                                                @PathVariable("comment-id") Long commentId){
        replyService.deleteReply(replyId);
        String response = "댓글삭제완료!!!";
        return new ResponseEntity<>(response,(HttpStatus.NO_CONTENT));
    }





}
