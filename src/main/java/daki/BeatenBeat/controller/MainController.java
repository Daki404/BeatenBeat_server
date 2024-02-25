package daki.BeatenBeat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Value("${domain}")
    private String domain;

    @GetMapping("/")
    public String redirectMain() {
        return "redirect:http://" + domain;
    }
}
