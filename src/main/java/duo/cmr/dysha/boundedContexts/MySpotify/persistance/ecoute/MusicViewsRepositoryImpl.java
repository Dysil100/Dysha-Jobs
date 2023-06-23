package duo.cmr.dysha.boundedContexts.MySpotify.persistance.ecoute;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class MusicViewsRepositoryImpl {

    private DaoMusicViewsRepository daoMusicViewsRepository;

    public Long countByMusicId(Long musicId) {
        return daoMusicViewsRepository.countByMusicId(musicId);
    }
}
