package duo.cmr.dysha.boundedContexts.dasandere.web.services;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.database.archiv.UserArchivEntity;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceSupreme {
    // TODO: 05.02.22 implement all html using fragments
    private AppUserService appUserService;
    private ConfirmationTokenService confirmationTokenService;

    public AppUser getUserByEmail(String email) {
        return (AppUser) appUserService.loadUserByUsername(email);
    }

    public AppUser getUserByToken(String token) {
       return (AppUser) appUserService.loadUserByUsername(confirmationTokenService.getToken(token).get().getUsername());
    }

    public boolean tokenExist(String token) {
        return confirmationTokenService.getToken(token).isPresent();
    }

    public List<AppUser> alleAppUsers() {
        return appUserService.alleUsers();
    }

    public List<UserArchivEntity> alleUsersArchiv() {
        return appUserService.alleUsersArchiv();
    }


    public AppUser authenticateUser(String username, String password) {
        return null;
    }
}
