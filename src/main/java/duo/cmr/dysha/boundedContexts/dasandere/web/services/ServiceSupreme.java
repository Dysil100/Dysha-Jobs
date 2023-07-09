package duo.cmr.dysha.boundedContexts.dasandere.web.services;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.discussion.Discussion;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.security.ChatDiscussionHash;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices.DiscussionService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.database.archiv.UserArchivEntity;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.ConfirmationTokenService;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceSupreme {
    // TODO: 05.02.22 implement all html using fragments
    private AppUserService appUserService;
    private ConfirmationTokenService confirmationTokenService;
    private DiscussionService discussionService;
    private ChatDiscussionHash ch;
    private EmailService emailService;
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

    public String getChatDiscussionHashFor(String userName1, String userName2) {
        Long id1 = appUserService.findByEmail(userName1).getId();
        Long id2 = appUserService.findByEmail(userName2).getId();
        String chatDiscussionHash = ch.getChatDiscussionHashFor(id1, id2);

        if (!discussionService.existByDiscussionHash(chatDiscussionHash) && (id2 != null) && (id1 != null)){
            discussionService.save(new Discussion(chatDiscussionHash));
        }
        return chatDiscussionHash;
    }

    @NotNull
    public Discussion getDiscussion(String discussionHash) {
        Discussion discussion = discussionService.finByDiscussionHash(discussionHash);
        if (discussion.getUsers().isEmpty()){
            List<AppUser> duoForHash = findDuoForHash(discussionHash);
            discussion.setUsers(duoForHash);
        }
        return discussion;
    }

    public List<AppUser> findDuoForHash(String discussionhash) {
        return appUserService.findAllByIds(ch.getUserIdsFromChatDiscussionHash(discussionhash));
    }

    public AppUser authenticateUser(String username, String password) {
        return null;
    }

    public void sendMail(String mail, String subjet, String comments) {
        emailService.send(mail, comments, subjet);
    }

    public void enableUserById(Long id) {
        AppUser byId = appUserService.findById(id);
        confirmationTokenService.updateByUsername(byId.getEmail());
        String token = confirmationTokenService.findByUsername(byId.getEmail()).get().getToken();
        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUserById(id);
    }

    public void disableUserById(Long id) {
        appUserService.disableAppUserById(id);
    }

    public void blockUserById(Long id) {
        appUserService.blockUserById(id);
    }

    public void deBlockUserById(Long id) {
        appUserService.deBlockUserById(id);
    }

    public void deleteUserById(Long id) {
        appUserService.deleteById(id);
    }
}
