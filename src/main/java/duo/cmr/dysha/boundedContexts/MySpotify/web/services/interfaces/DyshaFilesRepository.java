package duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshafiles.DyshaFiles;

public interface DyshaFilesRepository {
    void saveFile(String fileName,  byte[] bytesData);

    DyshaFiles findByName(String fileName);
}
