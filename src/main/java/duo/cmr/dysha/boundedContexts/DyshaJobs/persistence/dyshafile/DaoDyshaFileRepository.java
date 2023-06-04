package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshafile;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDyshaFileRepository extends CrudRepository<DyshaFileEntity, Long> {
    Iterable<DyshaFileEntity> findAllByTableNameAndUserIdAndEntityId(String tableName, Long userId, Long entityId);

    Iterable<DyshaFileEntity> findAllByTableNameAndEntityId(String tableName, Long entityId);

    Iterable<DyshaFileEntity> findAllByTableNameAndUserIdAndEntityIdAndFileType(String tableName, Long userId, Long id, String fileType);

    Iterable<DyshaFileEntity> findAllByTableNameAndEntityIdAndFileType(String tableName, Long entityId, String fileType);

    Iterable<DyshaFileEntity> findAllByEntityId(Long entityId);

    Iterable<DyshaFileEntity> findAllByUserId(Long userId);

    Iterable<DyshaFileEntity> findAllByTableNameAndUserIdAndFileType(String tableName, Long userId, String fileType);

    Iterable<DyshaFileEntity> findAllByTableName(String dyshaJobsLogo);

    Boolean existsByTableName(String dyshaJobsLogo);
}
