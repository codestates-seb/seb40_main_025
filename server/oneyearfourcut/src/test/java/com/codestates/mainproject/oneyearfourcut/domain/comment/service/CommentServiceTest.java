package com.codestates.mainproject.oneyearfourcut.domain.comment.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {


    /*    //comment가 artwork인지, gallery인지 판별하는 메소드
    public boolean checkCommentExistOn(Long commentId, Long placeId, CommentType commentType){
        if(commentType == CommentType.ARTWORK){
            Optional<Comment> commentOnArtwork = commentRepository.findAllByArtworkId(placeId);
            commentOnArtwork.orElseThrow(()->new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        } else{
            Gallery gallery = galleryService.findGallery(placeId);
            List<Comment> commentList = gallery.getCommentList();
            Optional.ofNullable(commentList).ifPresent(return true);
            Optional.ofNullable(commentId).ifPresent();
        }
    }*/
/*
    //findByListMethod //사용안함
    public List<Comment> findCommentList(Long galleryId, Long artworkId) {
        List<Comment> commentList;
        if (artworkId == null) {
            commentList =
                    commentRepository.findAllByCommentStatusAndGallery_GalleryId(VALID,galleryId, Sort.by(desc("createdAt")));
            if (commentList.isEmpty()) {
                throw new BusinessLogicException(ExceptionCode.GALLERY_NOT_FOUND);
            }
        }
        else {
            Optional<Gallery> givenGallery = galleryRepository.findById(galleryId);
            givenGallery.orElseThrow(() -> new BusinessLogicException(ExceptionCode.GALLERY_NOT_FOUND));
            commentList = commentRepository.findAllByCommentStatusAndArtworkId(VALID, artworkId, Sort.by(desc("createdAt")));
            if (commentList.isEmpty()) {
                throw new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND);
            }
        }
        return commentList;
    }
    */

}