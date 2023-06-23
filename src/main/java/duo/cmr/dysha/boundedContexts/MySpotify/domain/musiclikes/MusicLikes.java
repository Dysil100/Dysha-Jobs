package duo.cmr.dysha.boundedContexts.MySpotify.domain.musiclikes;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class MusicLikes {

    private Long id;
    private Long musicId;
    private Long userId;
    private String type;
    private LocalDateTime createdAt;

    public MusicLikes(Long musicId, Long userId, String type) {
        this.musicId = musicId;
        this.userId = userId;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }
}
