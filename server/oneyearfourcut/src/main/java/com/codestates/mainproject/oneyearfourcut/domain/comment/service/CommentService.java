package com.codestates.mainproject.oneyearfourcut.domain.comment.service;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.repository.ArtworkRepository;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentType;
import com.codestates.mainproject.oneyearfourcut.domain.comment.repository.CommentRepository;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.repository.GalleryRepository;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.service.GalleryService;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.repository.MemberRepository;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.service.ArtworkService;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus.DELETED;
import static com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus.VALID;
import static org.springframework.data.domain.Sort.Order.desc;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final GalleryRepository galleryRepository;
    private final ArtworkRepository artworkRepository;

    private final MemberService memberService;
    private final GalleryService galleryService;
    private final ArtworkService artworkService;


    public void createComment(Comment comment, Long galleryId, Long artworkId, Long memberId) {
        galleryService.verifiedGalleryExist(memberId);
        comment.setCommentStatus(VALID);
        Member member = new Member();
        member.setMemberId(memberId);
        comment.setMember(member);
        comment.setGallery(galleryService.findGallery(galleryId));
        if(artworkId != null){
            comment.setArtworkId(artworkId);
        }
        commentRepository.save(comment);
    }

    public List<Comment> findCommentList(Long galleryId, Long artworkId) {
        if (artworkId == null) {
            List<Comment> commentList =
                    commentRepository.findAllByCommentStatusAndGallery_GalleryId(VALID,galleryId, Sort.by(desc("createdAt")));
            if (commentList.isEmpty()) {
                throw new BusinessLogicException(ExceptionCode.GALLERY_NOT_FOUND);
            }
            return commentList;
        }
        else {
            Optional<Gallery> givenGallery = galleryRepository.findById(galleryId);
            givenGallery.orElseThrow(() -> new BusinessLogicException(ExceptionCode.GALLERY_NOT_FOUND));
            List<Comment> commentList = commentRepository.findAllByCommentStatusAndArtworkId(VALID, artworkId, Sort.by(desc("createdAt")));
            if (commentList.isEmpty()) {
                throw new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND);
            }
            return commentList;
        }
    }
    public Comment findComment(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        Comment foundComment = comment.orElseThrow(()->new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        if(foundComment.getCommentStatus() == DELETED) throw new BusinessLogicException(ExceptionCode.COMMENT_DELETED);
        return foundComment;
    }

    public boolean checkCommentExistOn(Long placeId, CommentType commentType){
        if(commentType == CommentType.ARTWORK){
            Optional<Artwork> artwork = artworkRepository.findById(placeId);
            artwork.orElseThrow(()->new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        } else{
            Gallery gallery = galleryService.findGallery(placeId);
            List<Comment> commentList = gallery.getCommentList();
            return Optional.ofNullable(commentList).isPresent(); //return true
        }
        return false;
    }

    public void deleteComment(Long galleryId, Long commentId) {
        checkCommentExistOn(galleryId,CommentType.GALLERY);
        Comment comment = findComment(commentId);
        comment.setCommentStatus(DELETED);
        commentRepository.save(comment);
    }





 /*   //Update method
    public Comment updateComment(Comment comment, Long memberId){
        Comment foundComment = findComment(comment.getCommentId());

        //member 검증. JWT 수정 필요.
        Long foundMember = mService.findMember(comment.getMember().getMemberId()).getMemberId();
        if(Objects.equals(memberId, foundMember)){

            Optional.ofNullable(comment.getContent())
                    .ifPresent(foundComment::setContent);

        }
        return cRepo.save(comment);
    }


    //Pagination method
    public Page<Comment> pageComments(int page, int size){
        PageRequest pr = PageRequest.of(page -1, size);
        return  cRepo.findAllByOrderByCreatedDateAsc(pr);
    }*/



}
