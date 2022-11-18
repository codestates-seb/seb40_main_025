package com.codestates.mainproject.oneyearfourcut.domain.gallery.controller;

import com.codestates.mainproject.oneyearfourcut.domain.gallery.dto.GalleryRequestDto;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.dto.GalleryResponseDto;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.mapper.GalleryMapper;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/galleries")
@RequiredArgsConstructor
public class GalleryController {
    private final GalleryService galleryService;
    private final GalleryMapper galleryMapper;
    //전시관 등록
    @PostMapping
    public ResponseEntity postGallery(@RequestBody GalleryRequestDto galleryRequestDto) {
        // jwt 토큰으로 회원id를 조회
        Long memberId = 1L;

        // 활성화된 전시관이 이미 존재하는지 확인하고 있으면 에러, 없으면 전시관 생성
        galleryService.verifiedOpenGalleryExist(memberId);

        Gallery postGallery = galleryMapper.galleryRequestDtoToGallery(galleryRequestDto);

        Gallery savedGallery = galleryService.createGallery(postGallery, memberId);

        //원래는 이 객체 반환
        GalleryResponseDto galleryResponseDto = galleryMapper.galleryToGalleryResponseDto(savedGallery);

        return new ResponseEntity<>(galleryResponseDto, HttpStatus.CREATED);
    }

    //전시관 조회
    @GetMapping("/{gallery-id}")
    public ResponseEntity getGallery(@PathVariable("gallery-id") Long galleryId) {
        Gallery findGallery = galleryService.findGallery(galleryId);

        GalleryResponseDto galleryResponseDto = galleryMapper.galleryToGalleryResponseDto(findGallery);

        return new ResponseEntity<>(galleryResponseDto, HttpStatus.OK);
    }

    //전시관 수정
    @PatchMapping("/{gallery-id}")
    public ResponseEntity patchGallery(@RequestBody GalleryRequestDto galleryRequestDto,
                                       @PathVariable("gallery-id") Long galleryId) {
        Gallery requestGallery = galleryMapper.galleryRequestDtoToGallery(galleryRequestDto);
        requestGallery.setGalleryId(galleryId);
        galleryService.modifyGallery(requestGallery);

        return new ResponseEntity(HttpStatus.OK);
    }

    //전시관 폐쇄
    @DeleteMapping("/{gallery-id}")
    public ResponseEntity deleteGallery(@PathVariable("gallery-id") Long galleryId) {
        // 전시관 폐쇄하는 로직
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
