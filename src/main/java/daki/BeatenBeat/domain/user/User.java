package daki.BeatenBeat.domain.user;

import daki.BeatenBeat.external.oauth2.userinfo.SocialType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nickname")
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "socialType")
    private SocialType socialType;
}
