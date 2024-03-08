package daki.BeatenBeat.domain.Jukebox;

import daki.BeatenBeat.domain.group.Group;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "info")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp modifiedDate;

    public void updateInfo(Song song) {
        this.song = song;
        this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }
}
