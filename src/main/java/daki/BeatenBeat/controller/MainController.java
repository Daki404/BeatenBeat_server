package daki.BeatenBeat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String redirectMain() {
        return "redirect:http://localhost:80";
        //return "redirect:http://www.time-traveler.site";
    }
}
