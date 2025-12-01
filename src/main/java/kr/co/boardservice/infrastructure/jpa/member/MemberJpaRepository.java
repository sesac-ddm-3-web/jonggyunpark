package kr.co.boardservice.infrastructure.jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity, Long> {
    Optional<MemberJpaEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}