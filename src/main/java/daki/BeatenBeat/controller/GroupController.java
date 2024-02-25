package daki.BeatenBeat.controller;

import daki.BeatenBeat.annotation.ReqUser;
import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.dto.GroupListResponseDTO;
import daki.BeatenBeat.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("")
    public GroupListResponseDTO getGroups(@ReqUser User user) {
        return groupService.getGroup(user);
    }

    @PostMapping("")
    public ResponseEntity<Object> makeGroup(@ReqUser User user, @RequestBody Map<String, String> body) {
        return groupService.buildGroup(user, body.get("name"));
    }

    @DeleteMapping("/{group_id}")
    public ResponseEntity<Object> deleteGroup(@PathVariable("group_id") Long id) {
        return groupService.deleteGroup(id);
    }

    @PutMapping("/{group_id}")
    public ResponseEntity<Object> putGroup(@PathVariable("group_id") Long id, @RequestBody Map<String, String> body) {
        return groupService.updateGroup(id, body.get("name"), body.get("url"));
    }

}
