package kr.co.boardservice.infrastructure.jpa.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentJpaEntity, Long> {
    List<CommentJpaEntity> findByArticleIdOrderByCreatedAtAsc(Long articleId);
}
