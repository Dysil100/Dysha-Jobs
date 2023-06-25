package duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshamusic.DyshaMusic;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.DyshaMusicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DyshaMusicService {
    private DyshaMusicRepository musicRepository;

    public DyshaMusic findByMusicFileName(String filename) {
        return musicRepository.findByMusicName(filename);
    }

    public List<DyshaMusic> getMusics() {
        // Implémentation de la récupération des vidéos depuis la source de données
        return musicRepository.findAll();
    }

    public void saveMusic(DyshaMusic music) {
        musicRepository.save(music);
    }

    // TODO: 19.05.2023 Save on a cloud and return the Url of the file


    public DyshaMusic findById(Long musicId) {
        return  musicRepository.findById(musicId);
    }

    public List<DyshaMusic> trySomePlaylistBy(Long musicId) {
        return musicRepository.trySomePlaylistBy(musicId);
    }
}
