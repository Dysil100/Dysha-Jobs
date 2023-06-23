package duo.cmr.dysha.boundedContexts.MySpotify.persistance.playlistmusic;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.playlistmusic.PlaylistMusic;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.playlistmusic.DaoPlaylistMusicRepository;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.playlistmusic.PlaylistMusicEntity;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.PlaylistMusicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class PlaylistMusicRepositoryImpl implements PlaylistMusicRepository {
    private DaoPlaylistMusicRepository daoPlaylistMusicRepository;
    @Override
    public void save(PlaylistMusic playlistMusic) {
        daoPlaylistMusicRepository.save(toPlaylistMusicEntity(playlistMusic));
    }

    private PlaylistMusicEntity toPlaylistMusicEntity(PlaylistMusic pm) {
        return new PlaylistMusicEntity(pm.getPlaylistId(), pm.getMusicId());
    }
}
