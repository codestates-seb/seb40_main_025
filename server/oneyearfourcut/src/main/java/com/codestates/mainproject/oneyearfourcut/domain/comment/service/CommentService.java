package com.codestates.mainproject.oneyearfourcut.domain.comment.service;

import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.CommentReqDto;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.mapper.CommentMapper;
import com.codestates.mainproject.oneyearfourcut.domain.comment.repository.CommentRepository;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.service.GalleryService;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus.DELETED;
import static com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus.VALID;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    private final MemberService memberService;
    private final GalleryService galleryService;

    @Transactional
    public void createCommentOnGallery(CommentReqDto requestDto, Long galleryId, Long memberId) {
        commentRepository.save(
                Comment.setComment(
                        galleryService.findGallery(galleryId),  //galleryId
                        null,  //artworkId
                        memberService.findMember(memberId),  //memberId
                        requestDto.getContent(), //Content
                        VALID
                )
        );
    }

    @Transactional
    public void createCommentOnArtwork(CommentReqDto requestDto, Long galleryId, Long artworkId, Long memberId) {
        commentRepository.save(
                Comment.setComment(
                        galleryService.findGallery(galleryId),  //galleryId
                        artworkId,  //artworkId
                        memberService.findMember(memberId),  //memberId
                        requestDto.getContent(), //Content
                        VALID
                )
        );
    }

    @Transactional
    public Page<Comment> findCommentByPage(Long galleryId, Long artworkId, int page, int size) {
        PageRequest pr = PageRequest.of(page - 1, size);
        Page<Comment> commentPage;
        galleryService.findGallery(galleryId);
        if (artworkId == null) {
            commentPage =
                    commentRepository.findAllByCommentStatusAndGallery_GalleryIdOrderByCommentIdDesc(VALID,galleryId, pr);
        }
        else {
            commentPage = commentRepository.findAllByCommentStatusAndArtworkIdOrderByCommentIdDesc(VALID, artworkId, pr);
        }
        if (commentPage.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        }
        return commentPage;
    }

    @Transactional
    protected Comment findComment(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        Comment foundComment = comment.orElseThrow(()->new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        if(foundComment.getCommentStatus() == DELETED) throw new BusinessLogicException(ExceptionCode.COMMENT_DELETED);
        return foundComment;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = findComment(commentId);
        comment.setCommentStatus(DELETED);
    }

    @Transactional
    public void modifyComment(Long commentId, CommentReqDto requestDto){
        Comment foundComment = findComment(commentId);
        Comment requestComment = mapper.commentRequestDtoToComment(requestDto);
        Optional.ofNullable(requestComment.getContent())
                .ifPresent(foundComment::setContent);
    }


}
