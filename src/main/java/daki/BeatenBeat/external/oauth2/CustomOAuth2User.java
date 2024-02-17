package daki.BeatenBeat.external.oauth2;

import daki.BeatenBeat.domain.user.User;
import lombok.Getter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private User user;

    public CustomOAuth2User(
                            Map<String, Object> attributes, String nameAttributeKey,
                            User user) {
        super(null, attributes, nameAttributeKey);
        this.user = user;
    }
}
