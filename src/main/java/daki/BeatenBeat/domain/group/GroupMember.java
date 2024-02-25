package daki.BeatenBeat.domain.group;

import daki.BeatenBeat.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "group_members")
@Getter
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
