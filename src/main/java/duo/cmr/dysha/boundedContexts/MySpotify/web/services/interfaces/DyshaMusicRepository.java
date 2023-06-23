package duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshamusic.DyshaMusic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DyshaMusicRepository {
    List<DyshaMusic> findAll();

    void save(DyshaMusic video);

    DyshaMusic findByMusicName(String filename);

    DyshaMusic findById(Long musicId);

    List<DyshaMusic> trySomePlaylistBy(Long musicId);
}
