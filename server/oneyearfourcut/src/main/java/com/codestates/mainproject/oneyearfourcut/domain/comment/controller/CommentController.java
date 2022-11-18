package com.codestates.mainproject.oneyearfourcut.domain.comment.controller;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.service.ArtworkService;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.*;

import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.mapper.CommentMapper;
import com.codestates.mainproject.oneyearfourcut.domain.comment.service.CommentService;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.service.GalleryService;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
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



    //댓글 등록 - 개별 작품(Artwork)
    @PostMapping("/artworks/{artwork-id}/comments")
    public ResponseEntity<Object> postCommentOnArtwork(
                @PathVariable("gallery-id") Long galleryId,
                @PathVariable("artwork-id") Long artworkId,
                @RequestBody CommentRequestDto commentRequestDto) {
        //comment 생성
        Comment comment = commentMapper.commentRequestDtoToComment(commentRequestDto);
        comment = commentService.createCommentOnArtwork(comment, artworkId); //저장
        Artwork artwork = artworkService.findArtwork(artworkId);
        artwork.addCommentList(comment);
        ArtworkCommentResponseDto response =
                commentMapper.commentToArtworkCommentResponseDto(comment);

        return new ResponseEntity<>(response, (HttpStatus.CREATED)); //생성 댓글 response
    }


    //댓글 리스트 조회 - 개별 작품(Artwork) with 페이지네이션
    @GetMapping("/{gallery-id}/artworks/{artwork-id}/comments")
    public ResponseEntity<Object> getCommentOnArtwork(@PathVariable("artwork-id") Long artworkId,
                                                      @RequestParam int page, @RequestParam int size) {


/*        Page<Comment> commentPage = commentService.pageComments(page, size);
        List<Comment> comments = commentPage.getContent();
        PageInfo pageInfo = new PageInfo(page, size, (int) commentPage.getTotalElements(), commentPage.getTotalPages());*/

        //artwork에 달린 댓글 조회
        Artwork artwork = artworkService.findArtwork(artworkId);
        List<ArtworkCommentResponseDto> response =
                        commentMapper.commentToArtworkCommentResponseDtoList(artwork.getCommentList());


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //댓글 등록 - 전체 작품(Gallery)
    @PostMapping("/{gallery-id}/comments")
    public ResponseEntity<Object> postCommentOnGallery(@PathVariable("gallery-id") Long galleryId,
                                         @RequestBody CommentRequestDto commentRequestDto) {

        Comment comment = commentMapper.commentRequestDtoToComment(commentRequestDto);
        comment = commentService.createCommentOnGallery(comment, galleryId);  //저장
        Gallery gallery = galleryService.findGallery(galleryId);
        gallery.addCommentList(comment);
        GalleryCommentResponseDto response =
                commentMapper.commentToGalleryCommentResponseDto(comment);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //https://wbluke.tistory.com/18
    //댓글 리스트 조회 - 개별 작품(Gallery) with Pagination
    @GetMapping("/{gallery-id}/comments")
    public ResponseEntity<Object> getGalleryCommentOnGallery(
            @PathVariable("gallery-id") Long galleryId,
            @RequestBody CommentRequestDto commentRequestDto,
            @RequestParam int page, @RequestParam int size) {
        /*Page<Comment> commentPage = commentService.pageComments(page, size);
        List<Comment> comments = commentPage.getContent();
        PageInfo pageInfo = new PageInfo(page, size, (int) commentPage.getTotalElements(), commentPage.getTotalPages());*/

        //gallery에 달린 댓글 조회
        Gallery gallery = galleryService.findGallery(galleryId);

        List<GalleryCommentResponseDto> response =
                commentMapper.commentToGalleryCommentResponseDtoList(gallery.getCommentList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



/*
    @GetMapping  //Gallery Comment 페이지네이션
    public ResponseEntity<Object> getCommentPages( @RequestParam int page, @RequestParam int size) {
        Page<Comment> commentPage = commentService.pageComments(page, size);
        List<Comment> comments = commentPage.getContent();
        List<GalleryCommentResponseDto> response =
                commentMapper.commentToGalleryCommentResponseDtoList(comments);
        PageInfo pageInfo = new PageInfo(page, size, (int) commentPage.getTotalElements(), commentPage.getTotalPages());
        return new ResponseEntity<>(new PageResponseDto<>(response, pageInfo), HttpStatus.OK);
    }
*/


}

