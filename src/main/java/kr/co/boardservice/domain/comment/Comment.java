package kr.co.boardservice.domain.comment;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String content;
    private Long authorId;
    private Long articleId;
    private LocalDateTime createdAt;

    public Comment(Long id, String content, Long authorId, Long articleId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.articleId = articleId;
        this.createdAt = createdAt;
    }

    public static Comment createNew(String content, Long authorId, Long articleId) {
        return new Comment(null, content, authorId, articleId, LocalDateTime.now());
    }

    public boolean isWrittenBy(Long memberId) {
        return this.authorId != null && this.authorId.equals(memberId);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
