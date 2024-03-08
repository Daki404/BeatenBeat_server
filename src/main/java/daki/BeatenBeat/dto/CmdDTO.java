package daki.BeatenBeat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Getter
public class CmdDTO {
    private Long groupId;
    private String cmd;
    private String url;

    public CmdDTO(Long groupId, String cmd, String url) {
        this.groupId = groupId;
        this.cmd = cmd;
        this.url = url;
    }

    public CmdDTO toDTO(Long groupId, String cmd, String url) {
        return CmdDTO.builder()
                .groupId(groupId)
                .cmd(cmd)
                .url(url)
                .build();
    }
}
