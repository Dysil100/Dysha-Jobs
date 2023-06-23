package duo.cmr.dysha.boundedContexts.dyshafile.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDyshaFilesRepository extends CrudRepository<DyshaFileEntity, Long> {

    DyshaFileEntity findByName(String fileName);

    Iterable<DyshaFileEntity> findAllByForUserId(Long forId);

    Iterable<DyshaFileEntity> findAllByTableName(String tableNmae);
}