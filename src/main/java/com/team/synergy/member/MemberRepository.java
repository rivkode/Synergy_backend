package com.team.synergy.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findById(String memberId);

    @Query(value = "SELECT count(*) FROM member", nativeQuery = true)
    Integer countMembers();

    Page<Member> findByNameContaining(String keyword, Pageable pageable);
}
