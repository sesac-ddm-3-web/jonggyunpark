package kr.co.boardservice.infrastructure.jpa.member;

import jakarta.persistence.*;
import kr.co.boardservice.domain.member.Member;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class MemberJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected MemberJpaEntity() {
    }

    public MemberJpaEntity(Long id, String username, String password, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public static MemberJpaEntity fromDomain(Member member) {
        return new MemberJpaEntity(
                member.getId(),
                member.getUsername(),
                member.getEncodedPassword(),
                member.getCreatedAt()
        );
    }

    public Member toDomain() {
        return new Member(id, username, password, createdAt);
    }
}
