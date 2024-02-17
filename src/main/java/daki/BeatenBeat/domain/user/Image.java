package daki.BeatenBeat.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setImageUrl(String imageUrl) {
        this.url = imageUrl;
    }

    public Image(User user, String imageUrl) {
        this.user = user;
        this.url = imageUrl;
    }
}

