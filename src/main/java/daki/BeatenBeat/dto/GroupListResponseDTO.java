package daki.BeatenBeat.dto;

import daki.BeatenBeat.domain.group.Group;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record GroupListResponseDTO(
        List<GroupResponseDTO> groups
) {
    public static GroupListResponseDTO toDTO(List<Group> groups) {
        List<GroupResponseDTO> groupDTOs = groups.stream()
                .map(GroupResponseDTO::toDTO)
                .collect(Collectors.toList());

        return GroupListResponseDTO.builder()
                .groups(groupDTOs)
                .build();
    }
}
