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
import org.springframework.transaction.annotation.Propagation;
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
        Artwork verifiedArtwork = findVerifiedArtwork(galleryId, artworkId);
        return verifiedArtwork;
    }

    public List<Artwork> findOneYearFourCut(long galleryId) {
        List<Artwork> oneYearFourCut = artworkRepository.findTop4ByGallery_GalleryId(galleryId,
                Sort.by(desc("voteCount"), desc("createdAt")));
        if (oneYearFourCut.size() == 0) {
            throw new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND);
        }

        return oneYearFourCut;
    }

    public Artwork updateArtwork(long galleryId, long artworkId, Artwork artwork) {
        Artwork verifiedArtwork = findVerifiedArtwork(galleryId, artworkId);

        // ################# S3 설정 시 이미지 관련 변경 예정 ########################
        Optional.ofNullable(artwork.getImg())
                .ifPresent(img -> verifiedArtwork.setImagePath("/" + img.getOriginalFilename()));
        Optional.ofNullable(artwork.getTitle())
                .ifPresent(title -> verifiedArtwork.setTitle(title));
        Optional.ofNullable(artwork.getContent())
                .ifPresent(content -> verifiedArtwork.setContent(content));

        return artworkRepository.save(verifiedArtwork);
    }

    public void deleteArtwork(long galleryId, long artworkId) {
        // 삭제 정책에 따를 예정
        Artwork findArtwork = findVerifiedArtwork(galleryId, artworkId);

        artworkRepository.delete(findArtwork);
    }

    // ================= 검증 관련 메서드 =================
    @Transactional(readOnly = true)
    /*
    검증되었다라는 조건이 특정 ArtworkId의 작품이 존재하는지도 있지만, 결국 해당 갤러리 안의 특정 작품이 존재하는지까지
    포함되어있다고 생각하여 검증 메서드를 합쳤습니다.
     */
    public Artwork findVerifiedArtwork(long galleryId, long artworkId) {
        Optional<Artwork> artworkOptional = artworkRepository.findById(artworkId);

        Artwork findArtwork = artworkOptional.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND));

        if (galleryId != findArtwork.getGallery().getGalleryId()) {
            throw new BusinessLogicException(ExceptionCode.ARTWORK_NOT_FOUND_FROM_GALLERY);
        }
        if (findArtwork.getGallery().getStatus() == GalleryStatus.CLOSED) {
            throw new BusinessLogicException(ExceptionCode.CLOSED_GALLERY);
        }

        return findArtwork;
    }

}

















