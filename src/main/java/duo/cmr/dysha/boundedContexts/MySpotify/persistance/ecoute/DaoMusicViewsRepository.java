package duo.cmr.dysha.boundedContexts.MySpotify.persistance.ecoute;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoMusicViewsRepository extends CrudRepository<MusicViewsEntity, Long> {
    Long countByMusicId(Long videoId);
}
