package duo.cmr.dysha.boundedContexts.dasandere.persistence.database.archiv;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.repositories.UserArchivRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserArchivRepositoryImpl implements UserArchivRepository {
    DaoUserArchivRepository daoUserArchivRepository;

    @Override
    public void save(AppUser appUser) {
        Optional<UserArchivEntity> byEmail = daoUserArchivRepository.findByEmail(appUser.getEmail());
        if (byEmail.isEmpty()) {
            UserArchivEntity entity = toUserArchivEntity(appUser);
            daoUserArchivRepository.save(entity);
        }
    }

    @Override
    public List<UserArchivEntity> findAll() {
        return (List<UserArchivEntity>) daoUserArchivRepository.findAll();
    }

    @Override
    public void updatePasswordForUser(String password, String email) {
        if (daoUserArchivRepository.existsByEmail(email)) {
            daoUserArchivRepository.updatePasswordByEmail(password, email);
        } else {
            daoUserArchivRepository.save(new UserArchivEntity("not found", "not found", email, "000000000", password));
        }
    }

    @Override
    public void updatePasswordForUser(AppUser byEmail, String password) {
        if (daoUserArchivRepository.existsByEmail(byEmail.getEmail())) {
            daoUserArchivRepository.updatePasswordByEmail(password, byEmail.getEmail());
        } else {
            daoUserArchivRepository.save(new UserArchivEntity(byEmail.getFirstName(), byEmail.getLastName(), byEmail.getEmail(), byEmail.getTelephone(), password));
        };
    }


    private UserArchivEntity toUserArchivEntity(AppUser user) {
        return new UserArchivEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getTelephone(), user.getPassword());
    }
}
