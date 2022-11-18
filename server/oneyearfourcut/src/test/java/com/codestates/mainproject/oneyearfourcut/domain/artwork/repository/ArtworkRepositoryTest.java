package com.codestates.mainproject.oneyearfourcut.domain.artwork.repository;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.entity.Gallery;
import com.codestates.mainproject.oneyearfourcut.domain.gallery.repository.GalleryRepository;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.domain.Sort.Order.desc;

@DataJpaTest
public class ArtworkRepositoryTest {

    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Artwork Entity - jpa 편의 메소드 테스트")
    public void saveArtworkGalleryRepository() {
        // given
        Member member1 = new Member();
        member1.setEmail("test1@gmail.com");
        member1.setNickname("주최자");
        Member admin = memberRepository.save(member1);

        Member member2 = new Member();
        member2.setEmail("test2@gmail.com");
        member2.setNickname("작성자");
        Member writer = memberRepository.save(member2);

        Gallery gallery = new Gallery();
        gallery.setMember(admin);
        gallery.setTitle("길동의 전시관");
        gallery.setContent("환영합니다.");
        Gallery savedGallery = galleryRepository.save(gallery);

        Artwork artwork = new Artwork();
        artwork.setTitle("작품제목");
        artwork.setContent("설명");
        artwork.setImagePath("/img.png");
        artwork.setMember(writer);
        artwork.setGallery(savedGallery);
        Artwork savedArtwork = artworkRepository.save(artwork);

        // when
        Long exceptedAdminId = admin.getMemberId();
        Long actualAdminId = savedArtwork.getGallery().getMember().getMemberId();

        String exceptedAdminName = admin.getNickname();
        String actualAdminName = savedArtwork.getGallery().getMember().getNickname();

        Long exceptedWriterId = writer.getMemberId();
        Long actualWriterId = savedArtwork.getMember().getMemberId();

        String exceptedWriterName = writer.getNickname();
        String actualWriterName = savedArtwork.getMember().getNickname();

        Long exceptedGalleryId = savedGallery.getGalleryId();
        Long actualGalleryId = savedArtwork.getGallery().getGalleryId();

        // than
        assertEquals(exceptedAdminId, actualAdminId);
        assertEquals(exceptedAdminName, actualAdminName);
        assertEquals(exceptedWriterId, actualWriterId);
        assertEquals(exceptedWriterName, actualWriterName);
        assertEquals(exceptedGalleryId, actualGalleryId);
    }

    @Test
    @DisplayName("ArtworkRepository - findAllByGalleryId() 테스트")
    public void findAllByGalleryIdTest() {
        /*
        findAllByGallery_GalleryId(Long galleryId, Sort sort) 테스트
        1. 해당 galleryId의 작품만 가져오는지?
        2. 생성시간 - 내림차순 정렬이 제대로 되는지?
         */
        Gallery gallery1 = new Gallery();
        Gallery savedGallery1 = galleryRepository.save(gallery1);
        Gallery gallery2 = new Gallery();
        Gallery savedGallery2 = galleryRepository.save(gallery2);

        Artwork artwork1_1 = new Artwork();
        artwork1_1.setTitle("갤러리1의 작품");
        artwork1_1.setContent("설명");
        artwork1_1.setImagePath("/img.png");
        artwork1_1.setGallery(savedGallery1);
        artworkRepository.save(artwork1_1);
        Artwork artwork1_2 = new Artwork();
        artwork1_2.setTitle("갤러리1의 작품");
        artwork1_2.setContent("설명");
        artwork1_2.setImagePath("/img.png");
        artwork1_2.setGallery(savedGallery1);
        artworkRepository.save(artwork1_2);
        Artwork artwork1_3 = new Artwork();
        artwork1_3.setTitle("갤러리1의 작품");
        artwork1_3.setContent("설명");
        artwork1_3.setImagePath("/img.png");
        artwork1_3.setGallery(savedGallery1);
        artworkRepository.save(artwork1_3);
        Artwork artwork2_1 = new Artwork();
        artwork2_1.setTitle("갤러리1의 작품");
        artwork2_1.setContent("설명");
        artwork2_1.setImagePath("/img.png");
        artwork2_1.setGallery(savedGallery2);
        artworkRepository.save(artwork2_1);

        List<Artwork> actualArtworkList = artworkRepository.findAllByGallery_GalleryId(savedGallery1.getGalleryId(),
                Sort.by(desc("createdAt")));
        List<Gallery> actual = actualArtworkList.stream().map(m -> m.getGallery()).collect(Collectors.toList());

        int exceptedArtworkListCount = gallery1.getArtworkList().size();
        int actualArtworkListCount = actualArtworkList.size();

        LocalDateTime exceptedLatestCreatedDate = artworkRepository.findAll().stream().
                filter(f -> f.getGallery().getGalleryId().equals(savedGallery1.getGalleryId()))
                .map(m -> m.getCreatedAt()).reduce((x, y) -> x.isAfter(y) ? x : y).get();
        LocalDateTime actualLatestCreatedDate = actualArtworkList.get(0).getCreatedAt();

        // DB에 저장한 특정 갤러리 id를 가진 작품의 수와 해당 갤러리 id에 속한 작품의 수가 일치하는지?
        assertThat(actualArtworkListCount).isEqualTo(exceptedArtworkListCount);
        // 특정 갤러리 id를 제외한 다른 갤러리 id를 가진 작품은 없는가?
        assertThat(actual).extracting("galleryId").doesNotContain(savedGallery2.getGalleryId());
        // 생성시간 - 내림차순 정렬이 제대로 되는지?
        assertThat(actualLatestCreatedDate).isEqualTo(exceptedLatestCreatedDate);
    }
}
