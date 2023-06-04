package duo.cmr.dysha.boundedContexts.dasandere.persistence.database.archiv;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.repositories.UserArchivRepository;
import lombok.AllArgsConstructor;
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
    public void updatePasswordByEmail(String password, String email) {
        UserArchivEntity userArchivEntity = daoUserArchivRepository.findByEmail(email).orElse(null);
        assert userArchivEntity != null;
        userArchivEntity.setPassword(password);
        daoUserArchivRepository.save(userArchivEntity);
    }

    private UserArchivEntity toUserArchivEntity(AppUser user) {
        return new UserArchivEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getTelephone(), user.getPassword());
    }
}
