package duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshaplaylist.DyshaPlaylist;

public interface DyshaPlaylistRepository {
    DyshaPlaylist findByUserIdAndPlaylistName(Long userId, String playlistName);
}
