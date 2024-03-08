package daki.BeatenBeat.domain.Jukebox;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "info_id")
    private Info info;

    @Column(name = "youtube_url")
    private String youtubeURL;
}
