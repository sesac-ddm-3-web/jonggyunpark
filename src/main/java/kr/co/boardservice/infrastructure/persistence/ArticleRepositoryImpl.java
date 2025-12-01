package kr.co.boardservice.infrastructure.persistence;

import kr.co.boardservice.domain.article.Article;
import kr.co.boardservice.domain.article.ArticleRepository;
import kr.co.boardservice.infrastructure.jpa.article.ArticleJpaEntity;
import kr.co.boardservice.infrastructure.jpa.article.ArticleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private final ArticleJpaRepository jpaRepository;

    public ArticleRepositoryImpl(ArticleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Article save(Article article) {
        ArticleJpaEntity entity = ArticleJpaEntity.fromDomain(article);
        ArticleJpaEntity saved = jpaRepository.save(entity);

        return saved.toDomain();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return jpaRepository.findById(id).map(ArticleJpaEntity::toDomain);
    }

    @Override
    public List<Article> findAllArticlesByCreatedAtDesc() {
        return jpaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(articleJpaEntity -> articleJpaEntity.toDomain())
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
