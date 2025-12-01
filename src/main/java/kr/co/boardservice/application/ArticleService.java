package kr.co.boardservice.application;

import jakarta.persistence.EntityNotFoundException;
import kr.co.boardservice.domain.article.Article;
import kr.co.boardservice.domain.article.ArticleRepository;
import kr.co.boardservice.domain.comment.Comment;
import kr.co.boardservice.domain.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }
    
    @Transactional
    public Article createArticle(Long authorId, String title, String content) {
        Article article = Article.createNew(title, content, authorId);

        return articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<Article> findAllArticles() {
        return articleRepository.findAllArticlesByCreatedAtDesc();
    }

    @Transactional
    public Article getArticleAndIncreaseViewCount(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        article.increaseViewCount();

        return articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<Comment> getComments(Long articleId) {
        return commentRepository.findByArticleIdOrderByCreatedAtAsc(articleId);
    }

    @Transactional
    public void deleteArticle(Long id, Long currentMemberId) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        if (!article.isWrittenBy(currentMemberId)) {
            throw new SecurityException("본인만 삭제할 수 있습니다.");
        }

        articleRepository.deleteById(id);
    }
}
