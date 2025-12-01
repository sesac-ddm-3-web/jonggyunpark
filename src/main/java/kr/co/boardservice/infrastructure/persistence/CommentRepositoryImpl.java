package kr.co.boardservice.infrastructure.persistence;

import kr.co.boardservice.domain.comment.Comment;
import kr.co.boardservice.domain.comment.CommentRepository;
import kr.co.boardservice.infrastructure.jpa.comment.CommentJpaEntity;
import kr.co.boardservice.infrastructure.jpa.comment.CommentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository jpaRepository;

    public CommentRepositoryImpl(CommentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Comment save(Comment comment) {
        CommentJpaEntity entity = CommentJpaEntity.fromDomain(comment);
        CommentJpaEntity saved = jpaRepository.save(entity);

        return saved.toDomain();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return jpaRepository.findById(id).map(CommentJpaEntity::toDomain);
    }

    @Override
    public List<Comment> findByArticleIdOrderByCreatedAtAsc(Long articleId) {
        return jpaRepository.findByArticleIdOrderByCreatedAtAsc(articleId).stream()
                .map(commentJpaEntity -> commentJpaEntity.toDomain())
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
