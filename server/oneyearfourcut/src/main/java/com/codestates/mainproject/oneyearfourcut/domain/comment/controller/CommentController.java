package com.codestates.mainproject.oneyearfourcut.domain.comment.controller;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.service.ArtworkService;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;

import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.mapper.CommentMapper;
import com.codestates.mainproject.oneyearfourcut.domain.comment.service.CommentService;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.repository.GalleryRepository;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.service.GalleryService;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import com.codestates.mainproject.oneyearfourcut.global.page.PageInfo;
import com.codestates.mainproject.oneyearfourcut.global.page.PageResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private final ArtworkService artworkService;
    private final GalleryService galleryService;
    private final CommentMapper commentMapper;

    private final MemberService memberService;
    private final GalleryRepository galleryRepository;

    //댓글 등록 - 전체 작품(Gallery)
    @PostMapping("/{gallery-id}/comments")
    public ResponseEntity<Object> postCommentOnGallery(@PathVariable("gallery-id") Long galleryId,
                                                       @RequestBody CommentRequestDto commentRequestDto) {
        Comment comment = commentMapper.commentRequestDtoToComment(commentRequestDto);
        long memberId = commentRequestDto.getMemberId(); //requestbody 멤버 번호
        comment = commentService.createComment(comment, galleryId, memberId);  //저장
        /*Gallery gallery = galleryService.findGallery(galleryId); //검증 및 매핑 과정.
        gallery.addCommentList(comment);
        GalleryCommentResponseDto response = commentMapper.commentToGalleryCommentResponseDto(comment)*/;
        String response = "댓글등록성공";
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //댓글 등록 - 개별 작품(Artwork)
    @PostMapping("/{gallery-id}/artworks/{artwork-id}/comments")
    public ResponseEntity<Object> postCommentOnArtwork(
            @PathVariable("gallery-id") Long galleryId,
            @PathVariable("artwork-id") Long artworkId,
            @RequestBody CommentRequestDto commentRequestDto){
        Comment comment = commentMapper.commentRequestDtoToComment(commentRequestDto);
        long memberId = commentRequestDto.getMemberId(); //requestbody 멤버 번호
        comment = commentService.createComment(comment, galleryId, artworkId,memberId);;
        /*Gallery gallery = galleryService.findGallery(galleryId);//검증 및 매핑 과정.
        gallery.addCommentList(comment);
        ArtworkCommentResponseDto response =
                commentMapper.commentToArtworkCommentResponseDto(comment);*/
        String response = "댓글등록성공";
        return new ResponseEntity<>(response, (HttpStatus.CREATED)); //생성 댓글 response

    }

    @GetMapping("/{gallery-id}/comments")
    public ResponseEntity<Object> getCommentOnGallery(@PathVariable("gallery-id") Long galleryId){
        List<Comment> commentList = commentService.findCommentOnGallery(galleryId);
        List<GalleryCommentResponse> response = commentMapper.commentToGalleryCommentResponseList(commentList);
        return new ResponseEntity<>(response, (HttpStatus.OK));
    }

    @GetMapping("/{gallery-id}/artworks/{artwork-id}/comments")
    public ResponseEntity<Object> getCommentOnArtwork(@PathVariable("gallery-id") Long galleryId,
                                                      @PathVariable("artwork-id") Long artworkId){
        Optional<Gallery> givenGallery = galleryRepository.findById(galleryId);
        givenGallery.orElseThrow(() -> new BusinessLogicException(ExceptionCode.GALLERY_NOT_FOUND));
        List<Comment> commentList = commentService.findCommentOnArtwork(artworkId);
        List<ArtworkCommentResponse> response = commentMapper.commentToArtworkCommentResponseList(commentList);
        return new ResponseEntity<>(response, (HttpStatus.OK));
    }

}

