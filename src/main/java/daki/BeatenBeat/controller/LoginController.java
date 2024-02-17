package daki.BeatenBeat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    @GetMapping("/kakao")
    public String kakaoRedirect() {
        return "redirect:/oauth2/authorization/kakao";
    }

    @GetMapping("/naver")
    public String naverRedirect() {
        return "redirect:/oauth2/authorization/naver";
    }

    @GetMapping("/google")
    public String googleRedirect() {
        return "redirect:/oauth2/authorization/google";
    }
}
