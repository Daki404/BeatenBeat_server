package daki.BeatenBeat.repository;

import daki.BeatenBeat.domain.group.GroupMember;
import daki.BeatenBeat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    List<GroupMember> findAllByUser(User user);
}
