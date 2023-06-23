package duo.cmr.dysha.boundedContexts.GoodDeals.web.security;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ChatDiscussionHash {

    public ChatDiscussionHash() {
    }

    public List<Long> getUserIdsFromChatDiscussionHash(String chatDiscussionHash) {
        // Extraire les deux IDs à partir du hash
        List<Long> result = new ArrayList<>();
        String[] split = chatDiscussionHash.split("\\.");
        long id1 = Long.parseLong(split[0]);
        long id2 = Long.parseLong(split[2]);
        result.add(id1);
        result.add(id2);
        return result;
    }

    public String getChatDiscussionHashFor(Long id1, Long id2) {
        String input = id1.compareTo(id2) < 0 ? id1 + "" + id2 : id2 + "" + id1; // Concaténer les chaînes dans l'ordre alphabétique
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return id1.compareTo(id2) < 0 ?  id1 + "." + sb.toString() + "." + id2 : id2 + "." + sb.toString() + "." + id1;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
