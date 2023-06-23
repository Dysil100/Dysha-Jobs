package duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshaplaylist.DyshaPlaylist;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.DyshaPlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DyshaPlaylistService {
    private DyshaPlaylistRepository playlistRepository;

    public DyshaPlaylist findByUserIdAndPlaylistName(Long userId, String playlistName) {
        return playlistRepository.findByUserIdAndPlaylistName(userId, playlistName);
    }
}
