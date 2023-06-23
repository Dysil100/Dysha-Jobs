package duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshaplaylist;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class DyshaPlaylist {

    private Long id;
    private String name;
    private Long userId;
    private LocalDateTime createdAt;

    public DyshaPlaylist(String name, Long userId, LocalDateTime createdAt) {
        this.name = name;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
