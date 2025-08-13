package com.cubeai.domain.member.repository;

import com.cubeai.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOAuthId(String oauthId);
}
