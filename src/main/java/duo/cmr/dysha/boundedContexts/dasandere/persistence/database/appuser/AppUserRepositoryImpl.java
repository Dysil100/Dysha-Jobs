package duo.cmr.dysha.boundedContexts.dasandere.persistence.database.appuser;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.interfaces.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AppUserRepositoryImpl implements AppUserRepository {
    private final DaoAppUserRepository daoAppUserRepository;

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return daoAppUserRepository.findByEmail(email).map(this::toAppUser);
    }

    @Override
    public void save(AppUser appUser) {
        daoAppUserRepository.save(toEntity(appUser));
    }

    @Override
    public void enableAppUser(String email) {
        daoAppUserRepository.updateEnabledUser(email);
    }

    @Override
    public void deleteByEmail(String email) {
        daoAppUserRepository.deleteByEmail(email);
    }

    @Override
    public void disableAppUser(String email) {
        daoAppUserRepository.updateDisableUser(email);
    }

    @Override
    public void updatePassword(String encode, String email) {
        daoAppUserRepository.updatePassword(encode, email);
    }

    @Override
    public List<AppUser> alle() {
        List<AppUser> alle = new ArrayList<>();
        daoAppUserRepository.findAll().forEach(e -> alle.add(toAppUser(e)));
        return alle.stream().sorted(Comparator.comparing(AppUser::getEmail, Comparator.naturalOrder())).collect(Collectors.toList());
    }

    @Override
    public List<AppUser> findByIds(List<Long> idsFromHash) {
        return toAppUserList(daoAppUserRepository.findAllById(idsFromHash));
    }

    @Override
    public AppUser findById(Long userId) {
        return toAppUser(daoAppUserRepository.findById(userId).get());
    }

    @Override
    public boolean existByEmail(String email) {
        return daoAppUserRepository.existsByEmail(email);
    }

    @Override
    public void blockUserById(Long id) {
        daoAppUserRepository.blockUserById(id);
    }

    @Override
    public void deBlockUserById(Long id) {
        daoAppUserRepository.deBlockUserById(id);
    }

    private List<AppUser> toAppUserList(Iterable<AppUserEntity> allByIds) {
        List<AppUser> appUsers = new ArrayList<>();
        allByIds.forEach(e -> appUsers.add(toAppUser(e)));
        return appUsers;

    }

    public AppUser toAppUser(AppUserEntity entity) {
        AppUser appUser = new AppUser(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getTelephone(), entity.getPassword(), entity.getRole(), entity.getLocked(), entity.getEnabled());
        appUser.setEnabled(entity.getEnabled());
        appUser.setLocked(entity.getLocked());
        return appUser;
    }

    public AppUserEntity toEntity(AppUser user) {
        return new AppUserEntity(user.getFirstName(), user.getLastName(), user.getUsername(), user.getTelephone(), user.getPassword(), user.getRole());
    }
}
