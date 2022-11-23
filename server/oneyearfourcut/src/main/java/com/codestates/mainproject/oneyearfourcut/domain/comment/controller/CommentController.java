package com.codestates.mainproject.oneyearfourcut.domain.comment.controller;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.repository.ArtworkRepository;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.service.ArtworkService;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;

import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentType;
import com.codestates.mainproject.oneyearfourcut.domain.comment.mapper.CommentMapper;
import com.codestates.mainproject.oneyearfourcut.domain.comment.service.CommentService;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.repository.GalleryRepository;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.service.GalleryService;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.repository.MemberRepository;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import com.codestates.mainproject.oneyearfourcut.global.page.ArtworkPageResponseDto;
import com.codestates.mainproject.oneyearfourcut.global.page.GalleryPageResponseDto;
import com.codestates.mainproject.oneyearfourcut.global.page.PageInfo;
import com.codestates.mainproject.oneyearfourcut.global.page.PageResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
        commentService.createCommentOnGallery(commentRequestDto, galleryId, 3L); //저장
        String response = "댓글등록성공";
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    //댓글 등록 - 개별 작품(Artwork)
    @PostMapping("/{gallery-id}/artworks/{artwork-id}/comments")
    public ResponseEntity<Object> postCommentOnArtwork(
            @PathVariable("gallery-id") Long galleryId,
            @PathVariable("artwork-id") Long artworkId,
            @RequestBody CommentRequestDto commentRequestDto){

        commentService.createCommentOnArtwork(commentRequestDto, galleryId,artworkId,3L); //저장
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
                                               @RequestBody CommentRequestDto commentRequestDto){
        Comment foundComment = commentService.findComment(commentId);
        Comment requestComment = commentMapper.commentRequestDtoToComment(commentRequestDto);
        commentService.modifyComment(requestComment,foundComment);
        String response = "댓글수정완료!!";
        return new ResponseEntity<>(response, (HttpStatus.OK));
    }

    @DeleteMapping("/{gallery-id}/comments/{comment-id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("gallery-id") Long galleryId,
                                                @PathVariable("comment-id") Long commentId){
        commentService.deleteComment(commentId);
        String response = "댓글삭제완료!!!";
        return new ResponseEntity<>(response,(HttpStatus.NO_CONTENT));
    }

}





