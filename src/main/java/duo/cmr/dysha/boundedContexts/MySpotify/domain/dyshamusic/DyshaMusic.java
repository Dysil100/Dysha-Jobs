package duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshamusic;


import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.MySpotify.domain.comment.DyshaComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DyshaMusic {
    private Long id;
    private String title;
    private String description;
    private String musicFileName;
    private String thumbnailFileName;
    private byte[] musicData;
    private byte[] thumbnailData;
    private Long views;
    private Long likes;
    private List<DyshaComment> comments;
    private LocalDateTime postedOn;
    private AppUser user;

    public DyshaMusic(String title, String description, String musicFileName, String thumbnailFileName, LocalDateTime postedOn) {
        this.title = title;
        this.description = description;
        this.musicFileName = musicFileName;
        this.thumbnailFileName = thumbnailFileName;
        this.postedOn = postedOn;
    }

    public DyshaMusic(String title, String description, String musicFileName, String thumbnailFileName, Long views, Long likes, LocalDateTime postedOn, AppUser user) {
        this.title = title;
        this.description = description;
        this.musicFileName = musicFileName;
        this.thumbnailFileName = thumbnailFileName;
        this.postedOn = postedOn;
        this.user = user;
        this.likes = likes;
        this.views = views;
    }

    public DyshaMusic() {}
}
