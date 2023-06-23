package duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.discussion;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.chatmessage.ChatMessage;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Discussion {

    private Long id;
    private String discussionHash;
    private List<ChatMessage> messages;
    private LocalDateTime createdAt;
    private List<AppUser> users;


    public Discussion(String discussionHash) {
        this.discussionHash = discussionHash;
        this.createdAt = LocalDateTime.now();
    }



    private AppUser getReceiver(String sender){
        return  (!users.get(0).getEmail().toLowerCase(Locale.ROOT).equals(sender.toLowerCase(Locale.ROOT)) ? users.get(0) : users.get(1));

    }
    public String getCurrentReceiver(String sender){
        if (users.isEmpty()){
            return "";
        } else if (users.size() == 1){
            return users.get(0).getEmail();
        } else return getReceiver(sender).getEmail();
    }

    public String getCurrentReceiverName(String sender){
        if (users.isEmpty()) {
            return "";
        } else if (users.size() == 1) return users.get(0).getFullName();
        else return getReceiver(sender).getFullName();
    }

    public ChatMessage getLastMessage(){
        return messages.isEmpty() ? new ChatMessage("", "" ,"", "", "") : messages.get(0);
    }
}
