package duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices;

import duo.cmr.dysha.boundedContexts.dasandere.persistence.database.token.ConfirmationTokenEntity;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static duo.cmr.dysha.boundedContexts.generalhelpers.DateTimeHelper.dateToString;


@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationTokenEntity token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationTokenEntity> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(dateToString(LocalDateTime.now()), token);
    }

    public Optional<ConfirmationTokenEntity> findByUsername(String email) {
        if (!existByEmail(email)) {
            newTokenForEmail(email);
            return confirmationTokenRepository.findByUsername(email);
        }
        return confirmationTokenRepository.findByUsername(email);
    }

    private boolean existByEmail(String email) {
        return confirmationTokenRepository.existsByEmail(email);
    }

    public void deleteByUsername(String email) {
        confirmationTokenRepository.deleteByUsername(email);
    }


    /**
     * @param email : generat a new token
     */
    public void newTokenForEmail(String email) {
        deleteByUsername(email);
        ConfirmationTokenEntity token = new ConfirmationTokenEntity(
                UUID.randomUUID().toString(), dateToString(LocalDateTime.now()),
                dateToString(LocalDateTime.now().plusMinutes(15)), email
        );
        saveConfirmationToken(token);
    }


    /**
     * @param email update the value of a token with username :email
     */
    public void updateByUsername(String email) {
        confirmationTokenRepository.updateByUsername(UUID.randomUUID().toString(), email);
    }
}
