package duo.cmr.dysha.boundedContexts.dasandere.persistence.database.token;

import duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ConfirmationTokenRepositoryImpl implements ConfirmationTokenRepository {
    DaoConfirmationToken daoConfirmationToken;

    @Override
    public void save(ConfirmationTokenEntity token) {
        daoConfirmationToken.save(token);
    }

    @Override
    public Optional<ConfirmationTokenEntity> findByToken(String token) {
        return daoConfirmationToken.findByToken(token);
    }

    @Override
    public void updateConfirmedAt(String now, String token) {
        daoConfirmationToken.updateConfirmedAt(now, token);
    }

    @Override
    public Optional<ConfirmationTokenEntity> findByUsername(String email) {
        return daoConfirmationToken.findByUsername(email);
    }

    @Override
    public void deleteByUsername(String email) {
        daoConfirmationToken.deleteByUsername(email);
    }

    @Override
    public void updateByUsername(String newToken, String email) {
        daoConfirmationToken.updateByUsername(newToken, email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return daoConfirmationToken.existsByUsername(email);
    }
}
