package daki.BeatenBeat.domain.group;

import daki.BeatenBeat.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "_group")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private User leader;

    public void updateGroup(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
