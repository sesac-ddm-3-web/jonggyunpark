package kr.co.boardservice.infrastructure.security;

import kr.co.boardservice.domain.member.Member;
import kr.co.boardservice.domain.member.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BoardUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public BoardUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new BoardUserDetails(member);
    }
}
