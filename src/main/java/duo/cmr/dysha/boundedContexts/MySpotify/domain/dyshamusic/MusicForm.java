package duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshamusic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MusicForm {
    private Long userId;
    private String title;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;

    public MusicForm(){}
}
