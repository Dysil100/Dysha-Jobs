package duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.chatmessage;


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
    private String message;
    private LocalDateTime createdAt;

    public ChatMessage(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    public static ChatMessage fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ChatMessage.class);
    }
}
