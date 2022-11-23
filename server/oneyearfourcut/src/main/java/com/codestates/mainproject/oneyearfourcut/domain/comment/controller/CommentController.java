package com.codestates.mainproject.oneyearfourcut.domain.comment.controller;

import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;

import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.mapper.CommentMapper;
import com.codestates.mainproject.oneyearfourcut.domain.comment.service.CommentService;
import com.codestates.mainproject.oneyearfourcut.global.page.ArtworkPageResponseDto;
import com.codestates.mainproject.oneyearfourcut.global.page.GalleryPageResponseDto;
import com.codestates.mainproject.oneyearfourcut.global.page.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
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
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;


    //댓글 등록 - 전체 작품(Gallery)
    @PostMapping("/{gallery-id}/comments")
    public ResponseEntity<Object> postCommentOnGallery(@PathVariable("gallery-id") Long galleryId,
                                                       @RequestBody CommentRequestDto commentRequestDto) {

        commentService.createCommentOnGallery(commentRequestDto, galleryId, 3L); //save
        String response = "댓글등록성공";
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    //댓글 등록 - 개별 작품(Artwork)
    @PostMapping("/{gallery-id}/artworks/{artwork-id}/comments")
    public ResponseEntity<Object> postCommentOnArtwork(
            @PathVariable("gallery-id") Long galleryId,
            @PathVariable("artwork-id") Long artworkId,
            @RequestBody CommentRequestDto commentRequestDto){

        commentService.createCommentOnArtwork(commentRequestDto, galleryId,artworkId,3L); //save
        String response = "댓글등록성공";
        return new ResponseEntity<>(response, (HttpStatus.CREATED)); //생성 댓글 response
    }

    //pagination
    @GetMapping("/{gallery-id}/comments")
    public ResponseEntity<Object> getGalleryComment(@PathVariable("gallery-id") Long galleryId,
                                                      @RequestParam int page/*, @RequestParam int size*/){
        int size = 10;
        Page<Comment> commentPage = commentService.findCommentByPage(galleryId, null, page, size);
        List<Comment> commentList = commentPage.getContent();
        PageInfo pageInfo = new PageInfo(page, size, (int) commentPage.getTotalElements(), commentPage.getTotalPages());
        List<GalleryCommentResponse> response = commentMapper.commentToGalleryCommentResponseList(commentList);
        return new ResponseEntity<>(new GalleryPageResponseDto<>(galleryId, response, pageInfo), (HttpStatus.OK));
    }

    @GetMapping("/{gallery-id}/artworks/{artwork-id}/comments")
    public ResponseEntity<Object> getArtworkComment(@PathVariable("gallery-id") Long galleryId,
                                                      @PathVariable("artwork-id") Long artworkId,
                                                      @RequestParam int page/*, @RequestParam int size*/) {
        int size = 10;
        Page<Comment> commentPage = commentService.findCommentByPage(galleryId, artworkId, page, size);
        List<Comment> commentList = commentPage.getContent();
        PageInfo pageInfo = new PageInfo(page, size, (int) commentPage.getTotalElements(), commentPage.getTotalPages());
        List<ArtworkCommentResponse> response = commentMapper.commentToArtworkCommentResponseList(commentList);
        return new ResponseEntity<>(new ArtworkPageResponseDto<>(artworkId, response, pageInfo), (HttpStatus.OK));
    }

    @PatchMapping("/{gallery-id}/comments/{comment-id}")
    public ResponseEntity<Object> patchComment(@PathVariable("gallery-id") Long galleryId,
                                               @PathVariable("comment-id") Long commentId,
                                               @RequestBody CommentRequestDto requestDto){
        commentService.modifyComment(commentId, requestDto);
        String response = "댓글수정완료!!";
        return new ResponseEntity<>(response, (HttpStatus.OK));
    }

    @DeleteMapping("/{gallery-id}/comments/{comment-id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("gallery-id") Long galleryId,
                                                @PathVariable("comment-id") Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}





