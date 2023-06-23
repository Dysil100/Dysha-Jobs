package duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.musiclikes.MusicLikes;

public interface MusicLikesRepository {
    void save(MusicLikes musicLikes);

    boolean existsByMusicIdAndUserId(Long musicId, Long userId);
}
