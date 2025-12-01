package kr.co.boardservice.infrastructure.jpa.comment;

import jakarta.persistence.*;
import kr.co.boardservice.domain.comment.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class CommentJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1024)
    private String content;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected CommentJpaEntity() {
    }

    public CommentJpaEntity(Long id, String content, Long memberId,
                            Long articleId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.articleId = articleId;
        this.createdAt = createdAt;
    }

    public static CommentJpaEntity fromDomain(Comment comment) {
        return new CommentJpaEntity(
                comment.getId(),
                comment.getContent(),
                comment.getAuthorId(),
                comment.getArticleId(),
                comment.getCreatedAt()
        );
    }

    public Comment toDomain() {
        return new Comment(
                this.id,
                this.content,
                this.memberId,
                this.articleId,
                this.createdAt
        );
    }
}
