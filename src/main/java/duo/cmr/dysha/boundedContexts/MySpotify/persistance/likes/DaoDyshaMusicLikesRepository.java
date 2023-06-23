package duo.cmr.dysha.boundedContexts.MySpotify.persistance.likes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDyshaMusicLikesRepository extends CrudRepository<MusicLikesEntity, Long> {
    Long countByMusicId(Long videoId);

    boolean existsByMusicIdAndUserId(Long muscId, Long userId);

    void deleteByMusicIdAndUserId(Long musicId, Long userId);
}
