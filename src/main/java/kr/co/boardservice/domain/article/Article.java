package kr.co.boardservice.domain.article;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Article(Long id, String title, String content, Long authorId,
                   long viewCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Article createNew(String title, String content, Long authorId) {
        LocalDateTime now = LocalDateTime.now();
        return new Article(null, title, content, authorId, 0L, now, now);
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public boolean isWrittenBy(Long memberId) {
        return this.authorId != null && this.authorId.equals(memberId);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public long getViewCount() {
        return viewCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
