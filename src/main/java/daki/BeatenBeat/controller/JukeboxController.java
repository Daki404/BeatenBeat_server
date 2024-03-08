package daki.BeatenBeat.controller;

import daki.BeatenBeat.annotation.ReqUser;
import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.dto.JukeboxResponseDTO;
import daki.BeatenBeat.service.JukeboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jukebox")
public class JukeboxController {

    private final JukeboxService jukeboxService;

    @GetMapping("/{group_id}")
    public JukeboxResponseDTO getJukebox(@ReqUser User user,
                                         @PathVariable("group_id") Long group_id) {
        return jukeboxService.getJukebox(user, group_id);
    }

    @PostMapping("/{group_id}")
    public ResponseEntity<Object> addJukebox(@ReqUser User user,
                                             @PathVariable("group_id") Long group_id,
                                             @RequestBody Map<String, String> body) {
        return jukeboxService.addMusic(user,
                                        group_id,
                                        body.get("youtube_URL"));
    }

    @PutMapping("/{group_id}")
    public ResponseEntity<Object> updateJukebox(@ReqUser User user,
                                             @PathVariable("group_id") Long group_id,
                                             @RequestBody Map<String, String> body) {
        return jukeboxService.updateJukebox(user,
                                            group_id,
                                            body.get("startURL"));
    }


}
