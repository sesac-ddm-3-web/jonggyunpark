package kr.co.boardservice.application;

import jakarta.persistence.EntityExistsException;
import kr.co.boardservice.domain.member.Member;
import kr.co.boardservice.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Member register(String username, String rawPassword) {
        if (memberRepository.existsByUsername(username)) {
            throw new EntityExistsException("이미 존재하는 ID 입니다.");
        }

        String encoded = passwordEncoder.encode(rawPassword); // SecurityConfig 에서 BCryptPasswordEncoder 구현체 생성

        Member member = Member.createNew(username, encoded);

        return memberRepository.save(member);
    }
}
