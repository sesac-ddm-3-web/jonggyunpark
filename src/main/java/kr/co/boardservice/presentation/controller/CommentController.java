package kr.co.boardservice.presentation.controller;

import kr.co.boardservice.application.CommentService;
import kr.co.boardservice.infrastructure.security.BoardUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String create(
            @RequestParam Long articleId,
            @RequestParam String content,
            @AuthenticationPrincipal BoardUserDetails userDetails
    ) {
        commentService.addComment(userDetails.getMemberId(), articleId, content);

        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable Long id,
            @RequestParam Long articleId,
            @AuthenticationPrincipal BoardUserDetails userDetails
    ) {
        commentService.deleteComment(id, userDetails.getMemberId());

        return "redirect:/articles/" + articleId;
    }
}
