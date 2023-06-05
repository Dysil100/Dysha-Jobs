package duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices;

import duo.cmr.dysha.boundedContexts.dasandere.domain.CustomEmailValidator;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.RegistrationRequest;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUserRole;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.database.token.ConfirmationTokenEntity;
import duo.cmr.dysha.boundedContexts.generalhelpers.generalresearch.MyGeneralSearcher;
import duo.cmr.dysha.boundedContexts.generalhelpers.matchers.MyMatchValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUserRole.*;
import static duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.stringToDate;

@Service
@AllArgsConstructor
public class RegistrationService {

    // TODO: 21.02.22 localhot to https://dysha-enterprise.com change for production

    @Value("${dyshajobs.admins}")
    private final List<String> admins;
    @Value("${dyshajobs.leaders}")
    private final List<String> leaders;

    private  AppUserService appUserService;
    private  CustomEmailValidator customEmailValidator;
    private  MyMatchValidator<RegistrationRequest> matchValidator;

    public String register(RegistrationRequest request) {
            if (customEmailValidator.test(request.getEmail())) {
                AppUserRole role = getRole(request.getEmail());
                AppUser appUser = new AppUser(
                        request.getFirstName(), request.getLastName(), request.getEmail(), request.getTelephone(), request.getPassword(), role
                );
                return appUserService.signUpUser(appUser);
            } else {
                return "Email " + request.getEmail() + " is not valid";
            }
    }

    private AppUserRole getRole(String email) {
        if (admins.contains(email)) return ROLE_ADMIN;
        if (leaders.contains(email)) return ROLE_LEADER;
        else return ROLE_USER;
    }

    @Transactional
    public String confirmToken(String token) {
        Optional<ConfirmationTokenEntity> token0 = appUserService.getToken(token.trim());
        if (token0.isEmpty()) {
            return "OOPS! Link expired or was already used"; // same as token not found
        }
        ConfirmationTokenEntity confirmationTokenEntity = token0.get();

        if (confirmationTokenEntity.getConfirmedAt() != null) {
            return "Email " + confirmationTokenEntity.getUsername() + " already confirmed";
        }

        LocalDateTime expiredAt = stringToDate(confirmationTokenEntity.getExpiredAt());

        if (expiredAt.isBefore(LocalDateTime.now())) {
            return appUserService.signUpUser((AppUser)
                    appUserService.loadUserByUsername(confirmationTokenEntity.getUsername()));
        }

        appUserService.setConfirmedTokenAt(confirmationTokenEntity);
        return "Confirmed";
    }

    public String recoverPassword(String email) {
        return appUserService.recoveryPassword(email);
    }

    public void updatePassword(String email, String password) {
        appUserService.updatePassword(email, password);
    }

    public boolean validateForm(RegistrationRequest request) {
        return matchValidator.matches(request);
    }
}
