package daki.BeatenBeat.repository;

import daki.BeatenBeat.domain.group.Group;
import daki.BeatenBeat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByLeader(User leader);
}
