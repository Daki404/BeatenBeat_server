package daki.BeatenBeat.controller;

import daki.BeatenBeat.annotation.ReqUser;
import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.dto.GroupListResponseDTO;
import daki.BeatenBeat.dto.GroupUsersReponseDTO;
import daki.BeatenBeat.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Object> makeGroup(@ReqUser User user,
                                            @RequestPart("name")String name,
                                            @RequestPart("file")MultipartFile file) {
        return groupService.buildGroup(user, name, file);
    }

    @GetMapping("/{group_id}")
    public GroupUsersReponseDTO getUsers(@PathVariable("group_id") Long id) {
        return groupService.getUsers(id);
    }

    @DeleteMapping("/{group_id}")
    public ResponseEntity<Object> deleteGroup(@PathVariable("group_id") Long id) {
        return groupService.deleteGroup(id);
    }

    @PutMapping("/{group_id}")
    public ResponseEntity<Object> putGroup(@PathVariable("group_id") Long id, @RequestBody Map<String, String> body) {
        return groupService.updateGroup(id, body.get("name"), body.get("url"));
    }

    @PostMapping("/{group_id}")
    public ResponseEntity<Object> inviteUser(@PathVariable("group_id") Long id,  @RequestBody Map<String, String> body) {
        return groupService.inviteUser(id, body.get("nickName"));
    }
}
