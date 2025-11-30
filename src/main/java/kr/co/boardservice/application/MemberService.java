package kr.co.boardservice.application;

import kr.co.boardservice.domain.member.Member;
import kr.co.boardservice.domain.member.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Member register(String username, String rawPassword) {
        if (memberRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 ID 입니다.");
        }

        String encoded = passwordEncoder.encode(rawPassword);
        Member member = Member.createNew(username, encoded);

        return memberRepository.save(member);
    }
}
