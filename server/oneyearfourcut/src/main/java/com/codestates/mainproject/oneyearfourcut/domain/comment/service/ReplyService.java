package com.codestates.mainproject.oneyearfourcut.domain.comment.service;

import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.ReplyResDto;
import com.codestates.mainproject.oneyearfourcut.domain.comment.dto.CommentRequestDto;
import com.codestates.mainproject.oneyearfourcut.domain.comment.entity.Reply;
import com.codestates.mainproject.oneyearfourcut.domain.comment.repository.ReplyRepository;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import com.codestates.mainproject.oneyearfourcut.global.page.ReplyListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus.DELETED;
import static com.codestates.mainproject.oneyearfourcut.domain.comment.entity.CommentStatus.VALID;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final CommentService commentService;
    private final MemberService memberService;

    //Create
    @Transactional
    public void createReply(CommentRequestDto commentRequestDto, Long commentId, Long memberId) {
        Reply reply = Reply.builder()
                .content(commentRequestDto.getContent())
                .comment(commentService.findComment(commentId))
                .member(memberService.findMember(memberId))
                .replyStatus(VALID)
                .build();
        replyRepository.save(reply);
    }

    //Read
    public ReplyListResponseDto<Object> getReplyList(Long commentId, Long memberId)  {
        List<Reply> replyList = findReplyList(commentId, memberId); //findReplyList에서 검증진행
        List<ReplyResDto> result = ReplyResDto.toReplyResponseDtoList(replyList);
        return new ReplyListResponseDto<>(commentId, result);
    }

    //Update
    @Transactional
    public void modifyReply(Long commentId, Long replyId, CommentRequestDto commentRequestDto,Long memberId){
        Reply foundReply = findReply(replyId);
        memberService.findMember(memberId);
        commentService.findComment(commentId);
        //--검증완료
        Reply requestReply =  commentRequestDto.toReplyEntity();
        Optional.ofNullable(requestReply.getContent())
                .ifPresent(foundReply::setContent);
    }

    //Delete
    @Transactional
    public void deleteReply(Long commentId, Long replyId, Long memberId) {
        Reply foundReply = findReply(replyId);
        memberService.findMember(memberId);
        commentService.findComment(commentId);
        //--검증완료
        foundReply.setReplyStatus(DELETED);
    }

    //----private-----
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
