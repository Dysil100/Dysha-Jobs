package duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.repositories;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;

import java.util.List;
import java.util.Optional;


public interface AppUserRepository {
   Optional<AppUser> findByEmail(String email);

    void save(AppUser appUser);

    void enableAppUser(String email);

    void deleteByEmail(String username);

    void disableAppUser(String email);

    void updatePassword(String encode, String email);

    List<AppUser> alle();

    List<AppUser> findByIds(List<Long> idsFromHash);

    AppUser findById(Long userId);

    boolean existByEmail(String email);

    void blockUserById(Long id);
   void deBlockUserById(Long id);
}
