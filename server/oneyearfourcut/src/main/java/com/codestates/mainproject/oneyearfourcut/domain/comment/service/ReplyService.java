package com.codestates.mainproject.oneyearfourcut.domain.comment.service;

import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.CommentRequestDto;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.ReplyResponseDto;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Comment;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import com.codestates.mainproject.oneyearfourcut.domain.comment.mapper.ReplyMapper;
import com.codestates.mainproject.oneyearfourcut.domain.comment.repository.CommentRepository;
import com.codestates.mainproject.oneyearfourcut.domain.comment.repository.ReplyRepository;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import com.codestates.mainproject.oneyearfourcut.global.page.ReplyListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus.DELETED;
import static com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus.VALID;
import static com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.GalleryStatus.OPEN;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper mapper;
    private final CommentService commentService;
    private final MemberService memberService;

    @Transactional
    public void createReply(CommentRequestDto requestDto, Long commentId, Long memberId) {
        replyRepository.save(
                Reply.setReply(
                        requestDto.getContent(), //Content
                        commentService.findComment(commentId), //commentId
                        memberService.findMember(memberId),  //memberId
                        VALID
                )
        );
    }
    public List<ReplyResponseDto> getReplyList(Long commentId, Long memberId)  {
        List<Reply> replyList = findReplyList(commentId, 3L);
        List<ReplyResponseDto> result = mapper.replyToReplyResponseDtoList(replyList);
        return result;
    }
    @Transactional
    public void modifyReply(Long replyId, CommentRequestDto requestDto){
        Reply foundReply = findReply(replyId);
        Reply requestReply = mapper.commentRequestDtoToReply(requestDto);
        Optional.ofNullable(requestReply.getContent())
                .ifPresent(foundReply::setContent);
    }
    @Transactional
    public void deleteReply(Long replyId) {
        Reply reply = findReply(replyId);
        reply.setReplyStatus(DELETED);
    }

    //----private-----//
    @Transactional
    private Reply findReply(Long replyId){
        Optional<Reply> reply = replyRepository.findById(replyId);
        Reply foundReply = reply.orElseThrow(()->new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        if(foundReply.getReplyStatus() == DELETED) throw new BusinessLogicException(ExceptionCode.COMMENT_DELETED);
        return foundReply;
    }
    @Transactional
    private List<Reply> findReplyList(Long commentId, Long memberId) {
        List<Reply> replyList;
        commentService.findComment(commentId);
        memberService.findMember(memberId);
        if (commentId != null) {
            replyList =
                    replyRepository.findAllByReplyStatusAndComment_CommentIdOrderByReplyIdDesc(VALID, commentId);
        }
        else {
           throw new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        }
        if (replyList.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        }
        return replyList;
    }



}
