package daki.BeatenBeat.dto;

import daki.BeatenBeat.domain.Jukebox.Info;
import daki.BeatenBeat.domain.group.Group;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;

@Builder
public record JukeboxResponseDTO(
        Long groupId,
        String songURL,
        Timestamp updateTime,
        List<String> songList
) {
    public static JukeboxResponseDTO toDTO(Group group, Info info, List<String> songList) {
        return JukeboxResponseDTO.builder()
                .groupId(group.getId())
                .songURL(info.getSong().getYoutubeURL())
                .updateTime(info.getModifiedDate())
                .songList(songList)
                .build();
    }
}
