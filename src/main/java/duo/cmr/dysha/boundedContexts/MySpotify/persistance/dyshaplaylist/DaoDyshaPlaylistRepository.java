package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshaplaylist;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDyshaPlaylistRepository extends CrudRepository<DyshaPlaylistEntity, Long> {
    DyshaPlaylistEntity findByNameAndUserId(String playlistName, Long userId);

    boolean existsByNameAndUserId(String playlistName, Long userId);
}
