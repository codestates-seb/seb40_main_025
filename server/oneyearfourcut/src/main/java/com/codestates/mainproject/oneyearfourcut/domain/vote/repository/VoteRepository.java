package com.codestates.mainproject.oneyearfourcut.domain.vote.repository;

import com.codestates.mainproject.oneyearfourcut.domain.artwork.entity.Artwork;
import com.codestates.mainproject.oneyearfourcut.domain.member.entity.Member;
import com.codestates.mainproject.oneyearfourcut.domain.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByMemberAndArtwork(Member member, Artwork artwork);
}
