package com.codestates.mainproject.oneyearfourcut.domain.artwork.service;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.artwork.repository.ArtworkRepository;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.GalleryStatus;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.service.GalleryService;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.repository.MemberRepository;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.BusinessLogicException;
import com.codestates.mainproject.oneyearfourcut.global.exception.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
@Transactional
public class ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final GalleryService galleryService;
    private final MemberRepository memberRepository; // MemberRepository -> MemberService 로 변경 예정

    public void createArtwork(long galleryId, Artwork artwork) {
        artwork.setGallery(galleryService.findGallery(galleryId));
        /*
        ### 이미지 업로드 관련
        MultipartFile img = artwork.getImg();
        // 파일명이 같더라도 충돌하지 않도록 UUID를 사용해 고유값을 넣도록 했습니다.
        String fileName = UUID.randomUUID() + "-" + img.getOriginalFilename();
        artwork.setImgPath(s3Url + fileName);

        ### 멤버 관련 - 토큰
        Member verifiedMember = memberRepository.findByEmail(SecurityContextHolder에서 가져온 유저 정보)
        artwork.setMember(verifiedMember);
        */

        // AccessToken 구현시 아래 로직 수정 예정
        Optional<Member> memberOptional = memberRepository.findById(2L);
        Member verifiedMember = memberOptional.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        artwork.setMember(verifiedMember);

        // 이미지 - 로컬환경 : "/파일명.확장자"형태로 DB에 저장 (S3 설정 시 삭제 예정)
        String localImgRoot = "/" + artwork.getImg().getOriginalFilename();
        artwork.setImagePath(localImgRoot);

        artworkRepository.save(artwork);
    }

    @Transactional(readOnly = true)
    public List<Artwork> findArtworkList(long galleryId) {
        List<Artwork> artworkList = artworkRepository.findAllByGallery_GalleryId(galleryId,
                Sort.by(desc("createdAt")));

        if (artworkList.size() == 0) {
            throw new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND);
        }

        return artworkList;
    }

    @Transactional(readOnly = true)
    public Artwork findArtwork(long galleryId, long artworkId) {
        Artwork findArtwork = findVerifiedArtwork(artworkId);
        verifyExistsArtworkInGallery(galleryId, findArtwork);
        return findArtwork;
    }

    public List<Artwork> findOneYearFourCut(long galleryId) {
        // 좋아요 로직에 따라 변경될 수 있음.
        List<Artwork> oneYearFourCut = artworkRepository.findTop4ByGallery_GalleryId(galleryId,
                // 정렬 기준은 좋아요 순, 만약 좋아요 수가 같을 경우 생성일 순
                Sort.by(desc("voteCount"), desc("createdAt")));
        if (oneYearFourCut.size() == 0) {
            throw new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND);
        }

        return oneYearFourCut;
    }

    public Artwork updateArtwork(long galleryId, long artworkId, Artwork artwork) {
        Artwork findArtwork = findVerifiedArtwork(artworkId);

        verifyExistsArtworkInGallery(galleryId, findArtwork);
        // ################# S3 설정 시 이미지 관련 변경 예정 ########################
        Optional.ofNullable(artwork.getImg())
                .ifPresent(img -> findArtwork.setImagePath("/" + img.getOriginalFilename()));
        Optional.ofNullable(artwork.getTitle())
                .ifPresent(title -> findArtwork.setTitle(title));
        Optional.ofNullable(artwork.getContent())
                .ifPresent(content -> findArtwork.setContent(content));

        return artworkRepository.save(findArtwork);
    }

    public void deleteArtwork(long galleryId, long artworkId) {
        // 삭제 정책에 따를 예정
        Artwork findArtwork = findVerifiedArtwork(artworkId);
        verifyExistsArtworkInGallery(galleryId, findArtwork);

        artworkRepository.delete(findArtwork);
    }

    // ================= 검증 관련 메서드 =================
    @Transactional(readOnly = true)
    public Artwork findVerifiedArtwork(long artworkId) {
        Optional<Artwork> artworkOptional = artworkRepository.findById(artworkId);

        Artwork verifiedArtwork = artworkOptional.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND));
        return verifiedArtwork;
    }

    private void verifyExistsArtworkInGallery(long galleryId, Artwork artwork) {
        if (galleryId != artwork.getGallery().getGalleryId()) {
            throw new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND_FROM_GALLERY);
        }
        if (artwork.getGallery().getStatus() == GalleryStatus.CLOSED) {
            throw new BusinessLogicException(ExceptionCode.CLOSED_GALLERY);
        }
    }
}

















