package daki.BeatenBeat.repository;

import daki.BeatenBeat.domain.user.Image;
import daki.BeatenBeat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {

    Optional<Image> findByUser(User user);

    @Modifying
    @Query("UPDATE Image i SET i.url=:imageURL WHERE i.user=:user")
    void updateUserImageURL(@Param(value = "imageURL") String imageURL,
                            @Param(value = "user") User user);

}
