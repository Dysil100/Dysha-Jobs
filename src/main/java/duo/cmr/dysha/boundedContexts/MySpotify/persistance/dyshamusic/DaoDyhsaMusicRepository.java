package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshamusic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDyhsaMusicRepository extends CrudRepository<DyshaMusicEntity, Long> {
    DyshaMusicEntity findByMusicFileName(String filename);
}
