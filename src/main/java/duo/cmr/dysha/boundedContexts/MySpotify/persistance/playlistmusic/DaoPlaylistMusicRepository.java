package duo.cmr.dysha.boundedContexts.MySpotify.persistance.playlistmusic;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoPlaylistMusicRepository extends CrudRepository<PlaylistMusicEntity, Long> {
}
