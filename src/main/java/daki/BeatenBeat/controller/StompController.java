package daki.BeatenBeat.controller;

import daki.BeatenBeat.dto.CmdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StompController {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/notice")
    public void message(@Payload CmdDTO message) {
        template.convertAndSend("/sub/room/" + message.getGroupId(), message);
    }
}
