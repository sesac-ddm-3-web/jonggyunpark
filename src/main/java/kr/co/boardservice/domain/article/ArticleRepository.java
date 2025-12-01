package kr.co.boardservice.domain.article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
    List<Article> findAllArticlesByCreatedAtDesc();
    void deleteById(Long id);
}
