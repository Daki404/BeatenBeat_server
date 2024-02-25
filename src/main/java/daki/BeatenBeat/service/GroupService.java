package daki.BeatenBeat.service;

import daki.BeatenBeat.domain.group.Group;
import daki.BeatenBeat.domain.group.GroupMember;
import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.dto.GroupListResponseDTO;
import daki.BeatenBeat.repository.GroupMemberRepository;
import daki.BeatenBeat.repository.GroupRepository;
import daki.BeatenBeat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public GroupListResponseDTO getGroup(User user) {
        List<Group> leadingGroups = groupRepository.findAllByLeader(user);
        List<Group> joinedGroups = groupMemberRepository.findAllByUser(user)
                .stream()
                .map(GroupMember::getGroup)
                .toList();

        List<Group> allGroups = new java.util.ArrayList<>(leadingGroups.stream()
                .filter(group -> !joinedGroups.contains(group))
                .toList());
        allGroups.addAll(joinedGroups);

        return GroupListResponseDTO.toDTO(allGroups);
    }

    @Transactional
    public ResponseEntity<Object> buildGroup(User user, String groupName) {
        Group group = Group.builder()
                .name(groupName)
                .leader(user)
                .build();

        group = groupRepository.save(group);
        if (group.getId() != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Transactional
    public ResponseEntity<Object> deleteGroup(Long id) {
        try {
            groupRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            // 삭제할 아이템이 존재하지 않는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found"); // 404 Not Found
        } catch (Exception e) {
            // 그 외의 예외 발생 시
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete item"); // 500 Internal Server Error
        }
    }

    @Transactional
    public ResponseEntity<Object> updateGroup(Long id, String name, String url) {
        Optional<Group> optionalGroup = groupRepository.findById(id);

        if(optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.updateGroup(name, url);
            groupRepository.save(group);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update group");
    }

    @Transactional
    public ResponseEntity<Object> inviteUser(Long id, String nickName) {
        Optional<User> optionUser = userRepository.findByNickName(nickName);
        Optional<Group> optionGroup = groupRepository.findById(id);

        if(optionUser.isEmpty() || optionGroup.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to invite User");
        }

        GroupMember groupMember = GroupMember.builder()
                .user(optionUser.get())
                .group(optionGroup.get())
                .build();

        groupMemberRepository.save(groupMember);
        return ResponseEntity.ok().build();
    }

}
