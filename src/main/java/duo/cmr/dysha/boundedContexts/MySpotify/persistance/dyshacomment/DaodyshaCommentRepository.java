package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshacomment;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaodyshaCommentRepository extends CrudRepository<DyshaCommentEntity, Long> {
    Iterable<DyshaCommentEntity> findAllByMusicId(Long musicId);
}
