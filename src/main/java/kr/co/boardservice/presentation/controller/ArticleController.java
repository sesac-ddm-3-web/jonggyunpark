package kr.co.boardservice.presentation.controller;

import kr.co.boardservice.application.ArticleService;
import kr.co.boardservice.application.CommentService;
import kr.co.boardservice.domain.article.Article;
import kr.co.boardservice.domain.comment.Comment;
import kr.co.boardservice.infrastructure.security.BoardUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping
    public String list(Model model) {
        List<Article> articles = articleService.listArticles();

        model.addAttribute("articles", articles);

        return "article/list";
    }

    @GetMapping("/{id}")
    public String detail(
        @PathVariable Long id,
        @AuthenticationPrincipal BoardUserDetails userDetails,
        Model model
    ) {
        Article article = articleService.getArticleAndIncreaseViewCount(id);
        List<Comment> comments = articleService.getComments(id);

        Long currentMemberId = userDetails.getMemberId();

        boolean isOwner = article.isWrittenBy(currentMemberId);

        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("currentMemberId", currentMemberId); // 댓글 작성 시 회원 확인용

        return "article/detail";
    }

    @GetMapping("/new")
    public String newForm() {
        return "article/new";
    }

    @PostMapping
    public String create(
            @RequestParam String title,
            @RequestParam String content,
            @AuthenticationPrincipal BoardUserDetails userDetails
    ) {
        articleService.createArticle(userDetails.getMemberId(), title, content);

        return "redirect:/articles";
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable Long id,
            @AuthenticationPrincipal BoardUserDetails userDetails
    ) {
        articleService.deleteArticle(id, userDetails.getMemberId());

        return "redirect:/articles";
    }
}
