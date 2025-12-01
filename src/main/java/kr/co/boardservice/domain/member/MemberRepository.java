package kr.co.boardservice.domain.member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
}
