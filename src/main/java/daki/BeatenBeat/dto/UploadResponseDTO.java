package daki.BeatenBeat.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

@Builder
@JsonPropertyOrder({"flag"})
public record UploadResponseDTO(
        boolean flag
) {
    public static UploadResponseDTO toDto(Boolean flag) {
        return UploadResponseDTO.builder()
                .flag(flag)
                .build();
    }
}
