package daki.BeatenBeat.external.oauth2.userinfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo{
    private Map<String, Object> response = (Map<String, Object>) attributes.get("response");

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }
    @Override
    public String getId() {
        if (response == null) {
            return null;
        }
        return (String) response.get("id");
    }

    @Override
    public String getNickname() {
        if (response == null) {
            return null;
        }
        return (String) response.get("nickname");
    }
}
