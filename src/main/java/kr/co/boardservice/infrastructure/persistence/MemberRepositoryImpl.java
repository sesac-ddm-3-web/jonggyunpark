package kr.co.boardservice.infrastructure.persistence;

import kr.co.boardservice.domain.member.Member;
import kr.co.boardservice.domain.member.MemberRepository;
import kr.co.boardservice.infrastructure.jpa.member.MemberJpaEntity;
import kr.co.boardservice.infrastructure.jpa.member.MemberJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository jpaRepository;

    public MemberRepositoryImpl(MemberJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Member save(Member member) {
        MemberJpaEntity entity = MemberJpaEntity.fromDomain(member);
        MemberJpaEntity saved = jpaRepository.save(entity);

        return saved.toDomain();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jpaRepository.findById(id).map(MemberJpaEntity::toDomain);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(memberJpaEntity -> memberJpaEntity.toDomain());
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }
}
