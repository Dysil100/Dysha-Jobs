package duo.cmr.dysha.boundedContexts.MySpotify.persistance.playlistmusic;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Table("playlistmusic")
public class PlaylistMusicEntity {
    private Long playlistId;
    private Long musicId;
}
