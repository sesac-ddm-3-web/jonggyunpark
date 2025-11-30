package kr.co.boardservice.domain.comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findByArticleIdOrderByCreatedAtAsc(Long articleId);
    void deleteById(Long id);
}
