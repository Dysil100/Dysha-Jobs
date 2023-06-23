package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshaplaylist;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshaplaylist.DyshaPlaylist;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.likes.DaoDyshaMusicLikesRepository;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.DyshaPlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@AllArgsConstructor
@Repository
public class DyshaPlaylistRepositoryImpl implements DyshaPlaylistRepository {

    private DaoDyshaPlaylistRepository daoDyshaPlaylistRepository;


    @Override
    public DyshaPlaylist findByUserIdAndPlaylistName(Long userId, String playlistName) {
        if (!daoDyshaPlaylistRepository.existsByNameAndUserId(playlistName, userId)) {
            daoDyshaPlaylistRepository.save(new DyshaPlaylistEntity(playlistName, userId, LocalDateTime.now()));
        }
        return toDyshaPlaylist(daoDyshaPlaylistRepository.findByNameAndUserId(playlistName, userId));
    }

    public DyshaPlaylist toDyshaPlaylist(DyshaPlaylistEntity e){
        return new DyshaPlaylist(e.getId(), e.getName(), e.getUserId(), e.getCreatedAt());
    }
}
