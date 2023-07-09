package duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.repositories;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.database.archiv.UserArchivEntity;

import java.util.List;

public interface UserArchivRepository {
    void save(AppUser appUser);

    List<UserArchivEntity> findAll();

    void updatePasswordForUser(String password, String email);

    void updatePasswordForUser(AppUser byEmail, String password);
}
