package duo.cmr.dysha.boundedContexts.MySpotify.persistance.likes;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.musiclikes.MusicLikes;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.MusicLikesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class MusicLikesRepositoryImpl implements MusicLikesRepository {
    private DaoDyshaMusicLikesRepository daoDyshaMusicLikesRepository;

    public Long countByMusicId(Long musicId) {
        return daoDyshaMusicLikesRepository.countByMusicId(musicId);
    }

    @Override
    public void save(MusicLikes musicLikes) {
        daoDyshaMusicLikesRepository.save(toLikeEntity(musicLikes));
    }

    @Override
    public boolean existsByMusicIdAndUserId(Long musicId, Long userId) {
        return daoDyshaMusicLikesRepository.existsByMusicIdAndUserId(musicId, userId);
    }

    private MusicLikesEntity toLikeEntity(MusicLikes like) {
        return new MusicLikesEntity(like.getMusicId(), like.getUserId(), like.getType(), like.getCreatedAt());
    }
}
