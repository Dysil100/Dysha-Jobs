package duo.cmr.dysha.boundedContexts.dasandere.persistence.database.appuser;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
public interface DaoAppUserRepository extends CrudRepository<AppUserEntity, Long> {

    Optional<AppUserEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value ="UPDATE users  Set enabled = true  WHERE email = :mail;")
    void updateEnabledUser(@Param("mail") String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM users WHERE email = :mail;")
    void deleteByEmail(@Param("mail")String email);

    @Transactional
    @Modifying
    @Query("UPDATE users  Set enabled = false  WHERE email = :mail;")
    void updateDisableUser(@Param("mail")String email);

    @Transactional
    @Modifying
    @Query("UPDATE users  Set password = :encoded  WHERE email = :mail;")
    void updatePassword(@Param("encoded")String encode, @Param("mail")String email);
    boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE users  Set locked = true  WHERE id = :id;")
    void blockUserById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE users  Set locked = false  WHERE id = :id;")
    void deBlockUserById(Long id);
}
