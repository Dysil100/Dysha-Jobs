package duo.cmr.dysha.boundedContexts.dyshafile.web.interfaces;

import duo.cmr.dysha.boundedContexts.dyshafile.domain.DyshaFile;

import java.util.List;

public interface DyshaFilesRepository {
    void saveFile(DyshaFile dyshaFile);

    DyshaFile findByName(String fileName);

    List<DyshaFile> findAllByUserId(Long forId);

    DyshaFile findById(Long fileId);

    DyshaFile findByUniqueName(String uniqueName);

    void deleteById(Long fileId);
}