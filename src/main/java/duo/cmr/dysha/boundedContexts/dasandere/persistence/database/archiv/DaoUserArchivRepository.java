package duo.cmr.dysha.boundedContexts.dasandere.persistence.database.archiv;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional(readOnly = true)
public interface DaoUserArchivRepository extends CrudRepository<UserArchivEntity, Long> {
    @Override
    Optional<UserArchivEntity> findById(Long aLong);

    Optional<UserArchivEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE archiv  Set password = :archiv  WHERE email = :mail;")
    void updatePasswordByEmail(@Param("archiv")String password, @Param("mail")String email);

    boolean existsByEmail(String email);
}
