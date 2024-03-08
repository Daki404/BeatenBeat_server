package daki.BeatenBeat.repository;

import daki.BeatenBeat.domain.Jukebox.Info;
import daki.BeatenBeat.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JukeboxRepository extends JpaRepository<Info, Long> {

    Optional<Info> findByGroup(Group group);
}
