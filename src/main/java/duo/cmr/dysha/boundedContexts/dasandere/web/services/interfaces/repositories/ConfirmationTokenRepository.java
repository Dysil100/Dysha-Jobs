package duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.repositories;

import duo.cmr.dysha.boundedContexts.dasandere.persistence.database.token.ConfirmationTokenEntity;

import java.util.Optional;

public interface ConfirmationTokenRepository {
    void save(ConfirmationTokenEntity token);

    Optional<ConfirmationTokenEntity> findByToken(String token);

    void updateConfirmedAt(String now, String token);

    Optional<ConfirmationTokenEntity> findByUsername(String username);

    void deleteByUsername(String email);

    void updateByUsername(String newToken, String email);

    boolean existsByEmail(String email);
}
