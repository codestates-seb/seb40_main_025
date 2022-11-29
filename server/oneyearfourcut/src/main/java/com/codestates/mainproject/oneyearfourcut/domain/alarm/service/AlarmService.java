package com.codestates.mainproject.oneyearfourcut.domain.alarm.service;

import com.codestates.mainproject.oneyearfourcut.domain.alarm.dto.AlarmReadCheckResponseDto;
import com.codestates.mainproject.oneyearfourcut.domain.alarm.dto.AlarmResponseDto;
import com.codestates.mainproject.oneyearfourcut.domain.alarm.entity.Alarm;
import static com.codestates.mainproject.oneyearfourcut.domain.alarm.entity.AlarmType.*;

import com.codestates.mainproject.oneyearfourcut.domain.alarm.entity.AlarmType;
import com.codestates.mainproject.oneyearfourcut.domain.alarm.repository.AlarmRepository;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.repository.ArtworkRepository;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.service.GalleryService;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.service.MemberService;
import com.codestates.mainproject.oneyearfourcut.global.page.AlarmListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmService {

    private final MemberService memberService;
    private final GalleryService galleryService;
    private final ArtworkRepository artworkRepository;

    private final AlarmRepository alarmRepository;


    @Transactional(readOnly=true)
    public List<AlarmResponseDto> getAlarmPagesByFilter(String filter, int page, Long memberId) {
        memberService.findMember(memberId);
        //Alarm List to Pageination logic
        Page<Alarm> alarmPage = findAlarmPagesByFilter(filter, memberId, page);
        List<Alarm> alarmList = alarmPage.getContent();

        return AlarmResponseDto.toAlarmResponseDtoList(alarmList);
    }

    @Transactional
    public void modifyAlarmReadTrue(Long memberId){
        alarmRepository.changeReadBoolean(true, memberId);
    }

    public AlarmReadCheckResponseDto checkReadAlarm(Long memberId) {
        Boolean alarmExist = alarmRepository.existsByMember_MemberIdAndReadCheck(memberId, true);
        if(alarmExist) {return AlarmReadCheckResponseDto.builder().readAlarmExist(false).message("현재 알림이 없습니다.").build();}
        else return AlarmReadCheckResponseDto.builder().readAlarmExist(true).message("읽지않은 알림이 존재합니다.").build();
    }

    @Transactional
    private Page<Alarm> findAlarmPagesByFilter(String filter, Long memberId, int page) {
        PageRequest pr = PageRequest.of(page - 1, 7);
        Page<Alarm> alarmPage;
        if(Objects.equals(filter, "ALL")){
            modifyAlarmReadTrue(memberId);
            alarmPage = alarmRepository.findAllByMember_MemberIdOrderByAlarmIdDesc(memberId,pr);
        }
        else{
            modifyAlarmReadTrue(memberId);
            alarmPage = alarmRepository.findAllByAlarmTypeAndMember_MemberIdOrderByAlarmIdDesc(
                    AlarmType.valueOf(filter), memberId, pr);
        }
        if(alarmPage.isEmpty()){
            Page.empty();
        }
        return alarmPage;
    }



    @Transactional
    public void createAlarm(Long locationId, Long memberIdProducer, AlarmType type){
        Member member = new Member();
        Artwork artwork = new Artwork();

        if(type != COMMENT_GALLERY) {
             member = artworkRepository.findById(locationId).orElseThrow().getMember();
            artwork = artworkRepository.findById(locationId).orElseThrow();
            Alarm alarm = Alarm.builder()
                    .member(member)
                    .memberIdProducer(memberIdProducer)
                    .alarmType(type)
                    .artworkId(artwork.getArtworkId())
                    .artworkTitle(artwork.getTitle())
                    .userNickname(memberService.findMember(member.getMemberId()).getNickname())
                    .readCheck(false)
                    .build();
            alarmRepository.save(alarm);
        }
        else { member = galleryService.findGallery(locationId).getMember();

            Alarm alarm = Alarm.builder()
                    .member(member)
                    .memberIdProducer(memberIdProducer)
                    .alarmType(type)
                    .userNickname(memberService.findMember(member.getMemberId()).getNickname())
                    .readCheck(false)
                    .build();

            alarmRepository.save(alarm);
        }
    }
}
