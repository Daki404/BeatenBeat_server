package daki.BeatenBeat.dto;

import daki.BeatenBeat.domain.group.Group;
import lombok.Builder;

import java.util.List;

@Builder
public record GroupUsersReponseDTO(
        Long id,
        List<String> userNameList
) {
  public static GroupUsersReponseDTO toDTO(Group group, List<String> nameList) {
        return GroupUsersReponseDTO.builder()
                .id(group.getId())
                .userNameList(nameList)
                .build();
  }
}
