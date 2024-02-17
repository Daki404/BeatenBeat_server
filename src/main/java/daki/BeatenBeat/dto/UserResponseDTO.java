package daki.BeatenBeat.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.external.oauth2.userinfo.SocialType;
import lombok.Builder;

@Builder
@JsonPropertyOrder({"id", "nickname", "imageURL"})
public record UserResponseDTO(
    String id,
    String nickname,
    String imageURL,
    SocialType socialType
) {
    public static UserResponseDTO toDTO(User user, String imageURL) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .nickname(user.getNickName())
                .imageURL(imageURL)
                .socialType(user.getSocialType())
                .build();
    }
}
