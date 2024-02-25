package daki.BeatenBeat.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import daki.BeatenBeat.domain.group.Group;
import lombok.Builder;

@Builder
@JsonPropertyOrder({"name", "image"})
public record GroupResponseDTO(
        Long id,
        String leader_id,
        String name,
        String imageURL
) {
   public static GroupResponseDTO toDTO(Group group) {
       return GroupResponseDTO.builder()
               .id(group.getId())
               .leader_id(group.getLeader().getId())
               .name(group.getName())
               .imageURL(group.getUrl())
               .build();
   }
}
