package kr.co.boardservice.infrastructure.jpa.article;

import jakarta.persistence.*;
import kr.co.boardservice.domain.article.Article;

import java.time.LocalDateTime;

@Entity
@Table(name = "article")
public class ArticleJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 256)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "view_count", nullable = false)
    private long viewCount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected ArticleJpaEntity() {
    }

    public ArticleJpaEntity(Long id, String title, String content, Long memberId,
                            long viewCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ArticleJpaEntity fromDomain(Article article) {
        return new ArticleJpaEntity(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getAuthorId(),
                article.getViewCount(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }

    public Article toDomain() {
        return new Article(
                this.id,
                this.title,
                this.content,
                this.memberId,
                this.viewCount,
                this.createdAt,
                this.updatedAt
        );
    }
}
