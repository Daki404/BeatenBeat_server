package daki.BeatenBeat.external.oauth2;

import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.external.oauth2.userinfo.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey;
    private OAuth2UserInfo oAuth2UserInfo;

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    public static OAuthAttributes of(SocialType socialType,
                                     String userNameAttribute, Map<String, Object> attributes) {
        if (socialType == SocialType.NAVER) {
            return ofNaver(userNameAttribute, attributes);
        }
        if (socialType == SocialType.KAKAO) {
            return ofKakao(userNameAttribute, attributes);
        }
        return ofGoogle(userNameAttribute, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttrivuteName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttrivuteName)
                .oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }
    private static OAuthAttributes ofKakao(String userNameAttrivuteName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttrivuteName)
                .oAuth2UserInfo(new KaKaoOAuth2UserInfo(attributes))
                .build();
    }
    private static OAuthAttributes ofGoogle(String userNameAttrivuteName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttrivuteName)
                .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public User toEntity(SocialType socialType, OAuth2UserInfo oAuth2UserInfo) {
        return User.builder()
                .socialType(socialType)
                .id(oAuth2UserInfo.getId())
                .nickName(oAuth2UserInfo.getNickname())
                .build();
    }
}
