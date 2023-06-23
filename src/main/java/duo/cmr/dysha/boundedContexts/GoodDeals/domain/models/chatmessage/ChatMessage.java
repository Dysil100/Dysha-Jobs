package duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.chatmessage;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChatMessage {
    private Long id;
    private String sender;
    private String receiver;
    private String sujet;
    private String message;
    private String discussionHash;
    private LocalDateTime createdAt;

    public ChatMessage(String sender, String receiver, String sujet, String message, String discussionHash) {
        this.sender = sender;
        this.receiver = receiver;
        this.sujet = sujet;
        this.message = message;
        this.discussionHash = discussionHash;
        this.createdAt = LocalDateTime.now();
    }

    public static ChatMessage fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ChatMessage.class);
    }
}
