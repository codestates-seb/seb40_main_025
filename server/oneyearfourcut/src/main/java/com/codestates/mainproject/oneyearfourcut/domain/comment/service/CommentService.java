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

    //댓글 생성 메소드, 1)galleryservice 통해서 회원/갤러리검증, 2)댓글VALID설정 3)memberid, gallery set(양쪽) 4)artwork set 5)save
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

    //댓글 리스트 받아오는 메소드 --> 1)gallery 댓글은 else로 넘어감, 2)jpa메소드를 통해서 VALID 한 댓글 List로 조회, 3) exceptioncode
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

    //comment jpa레포 존재여부 검증 메소드
    public Comment findComment(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        Comment foundComment = comment.orElseThrow(()->new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        if(foundComment.getCommentStatus() == DELETED) throw new BusinessLogicException(ExceptionCode.COMMENT_DELETED);
        return foundComment;
    }

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


    //comment 삭제 메소드, 1)pathvariable를 통해서 gallery존재 확인, 2)repo존재 확인 3)status deleted 4)save
    public void deleteComment(Long commentId) {
        Comment comment = findComment(commentId);
        comment.setCommentStatus(DELETED);
        commentRepository.save(comment);
    }

    //comment update
    public Comment modifyComment(Comment reqeustComment, Comment foundComment){
        Optional.ofNullable(reqeustComment.getContent())
                .ifPresent(foundComment::setContent);
        return commentRepository.save(foundComment);
    }

/*    //Pagination method
    public Page<Comment> pageComments(int page, int size){
        PageRequest pr = PageRequest.of(page -1, size);
        return  cRepo.findAllByOrderByCreatedDateAsc(pr);
    }*/
}
