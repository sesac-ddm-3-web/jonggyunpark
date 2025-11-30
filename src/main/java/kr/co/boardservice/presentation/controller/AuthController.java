package kr.co.boardservice.presentation.controller;

import kr.co.boardservice.application.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(
        @RequestParam String username,
        @RequestParam String password
    ) {
        memberService.register(username, password);

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }
}
