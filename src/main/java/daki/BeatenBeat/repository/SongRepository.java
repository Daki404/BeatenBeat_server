package daki.BeatenBeat.repository;

import daki.BeatenBeat.domain.Jukebox.Info;
import daki.BeatenBeat.domain.Jukebox.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByInfo(Info info);

    Song findByYoutubeURL(String url);

}
