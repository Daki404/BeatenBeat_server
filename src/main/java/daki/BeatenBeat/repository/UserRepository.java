package daki.BeatenBeat.repository;

import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.external.oauth2.userinfo.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findBySocialTypeAndId(SocialType socialType, String id);

    Optional<User> findByNickName(String nickname);

}
