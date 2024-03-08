package daki.BeatenBeat.service;

import daki.BeatenBeat.domain.Jukebox.Info;
import daki.BeatenBeat.domain.Jukebox.Song;
import daki.BeatenBeat.domain.group.Group;
import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.dto.JukeboxResponseDTO;
import daki.BeatenBeat.repository.GroupRepository;
import daki.BeatenBeat.repository.JukeboxRepository;
import daki.BeatenBeat.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JukeboxService {
    private final GroupRepository groupRepository;
    private final JukeboxRepository jukeboxRepository;
    private final SongRepository songRepository;

    @Transactional(readOnly = true)
    public JukeboxResponseDTO getJukebox(User user, Long group_id) {
        Group group = groupRepository.findById(group_id).get();
        Info info = jukeboxRepository.findByGroup(group).get();
        List<String> songList = songRepository.findAllByInfo(info)
                .stream()
                .map(Song::getYoutubeURL)
                .toList();

        return JukeboxResponseDTO.toDTO(group, info, songList);
    }

    @Transactional
    public ResponseEntity<Object> addMusic(User user, Long group_id, String youtubeURL) {
        Group group = groupRepository.findById(group_id).get();
        Info info = jukeboxRepository.findByGroup(group).get();
        Song song = Song.builder()
                .info(info)
                .youtubeURL(youtubeURL)
                .build();

        song = songRepository.save(song);
        if (song.getId() != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Transactional
    public ResponseEntity<Object> updateJukebox(User user, Long group_id, String youtube_URL) {
        Group group = groupRepository.findById(group_id).get();
        Info info = jukeboxRepository.findByGroup(group).get();
        Song song = songRepository.findByYoutubeURL(youtube_URL);

        info.updateInfo(song);
        return ResponseEntity.ok().build();
    }
}