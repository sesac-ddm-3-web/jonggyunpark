package kr.co.boardservice.infrastructure.jpa.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleJpaRepository extends JpaRepository<ArticleJpaEntity, Long> {
    List<ArticleJpaEntity> findAllByOrderByCreatedAtDesc();
}