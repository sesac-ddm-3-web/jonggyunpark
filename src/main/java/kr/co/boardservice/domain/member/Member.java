package kr.co.boardservice.domain.member;

import java.time.LocalDateTime;

public class Member {
    private final Long id;
    private final String username;
    private final String encodedPassword;
    private final LocalDateTime createdAt;

    public Member(Long id, String username, String encodedPassword, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.createdAt = createdAt;
    }

    public static Member createNew(String username, String encodedPassword) {
        return new Member(null, username, encodedPassword, LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
