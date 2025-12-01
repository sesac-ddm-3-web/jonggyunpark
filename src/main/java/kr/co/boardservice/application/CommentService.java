package kr.co.boardservice.application;

import jakarta.persistence.EntityNotFoundException;
import kr.co.boardservice.domain.article.ArticleRepository;
import kr.co.boardservice.domain.comment.Comment;
import kr.co.boardservice.domain.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Comment addComment(Long memberId, Long articleId, String content) {
        articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        Comment comment = Comment.createNew(content, memberId, articleId);

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long currentMemberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 없습니다."));

        if (!comment.isWrittenBy(currentMemberId)) {
            throw new SecurityException("본인만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(commentId);
    }
}
