package duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.playlistmusic.PlaylistMusic;

public interface PlaylistMusicRepository {
    void save(PlaylistMusic playlistMusic);
}
