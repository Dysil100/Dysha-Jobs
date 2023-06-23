package duo.cmr.dysha.boundedContexts.MySpotify.domain.playlistmusic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlaylistMusic {
    private Long playlistId;
    private Long musicId;
}
