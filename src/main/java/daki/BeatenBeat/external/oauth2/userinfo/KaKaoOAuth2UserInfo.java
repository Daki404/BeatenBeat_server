package daki.BeatenBeat.external.oauth2.userinfo;

import java.util.Map;

public class KaKaoOAuth2UserInfo extends OAuth2UserInfo{

    public KaKaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }
    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getNickname() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        if (properties == null) {
            return null;
        }

        return (String) properties.get("nickname");
    }
}
