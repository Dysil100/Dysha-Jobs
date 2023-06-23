package duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.playlistmusic.PlaylistMusic;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.PlaylistMusicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class PlaylistMusicService {
    private PlaylistMusicRepository musicRepository;


    public void save(PlaylistMusic playlistMusic) {
        musicRepository.save(playlistMusic);
    }
}
