package duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.musiclikes.MusicLikes;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.MusicLikesRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MusicLikesService {

    private MusicLikesRepository likesRepository;

    public void saveLike(MusicLikes musicLikes) {
        likesRepository.save(musicLikes);
    }

    public boolean existsByMusicIdAndUserId(Long musicId, Long userId) {
        return likesRepository.existsByMusicIdAndUserId(musicId, userId);
    }
}
