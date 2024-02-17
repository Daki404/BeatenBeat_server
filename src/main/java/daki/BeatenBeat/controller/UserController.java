package daki.BeatenBeat.controller;

import daki.BeatenBeat.annotation.ReqUser;
import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.dto.UploadResponseDTO;
import daki.BeatenBeat.dto.UserResponseDTO;
import daki.BeatenBeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponseDTO getMyInfo(@ReqUser User user) {
        return userService.getUserInfo(user);
    }

    @PostMapping("/me")
    public UploadResponseDTO uploadProfileImage(@RequestPart("file") MultipartFile file,
                                                @ReqUser User user) {
        return userService.uploadProfileImage(file, user);
    }
}
