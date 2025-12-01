package kr.co.boardservice.presentation.controller;

import kr.co.boardservice.application.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/auth/signup", method = RequestMethod.GET)
    public String signupForm() {
        return "auth/signup";
    }

    @RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
    public String signup(
        @RequestParam String username,
        @RequestParam String password
    ) {
        memberService.register(username, password);

        return "redirect:/auth/login";
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String loginForm() {
        return "auth/login";
    }
}
