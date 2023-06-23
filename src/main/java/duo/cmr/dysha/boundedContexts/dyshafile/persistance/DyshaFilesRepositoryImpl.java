package duo.cmr.dysha.boundedContexts.dyshafile.persistance;

import duo.cmr.dysha.boundedContexts.dyshafile.domain.DyshaFile;
import duo.cmr.dysha.boundedContexts.dyshafile.web.interfaces.DyshaFilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DyshaFilesRepositoryImpl implements DyshaFilesRepository {
   DaoDyshaFilesRepository daoDyshaFilesRepository;
    @Override
    public void saveFile(DyshaFile dyshaFile) {
        daoDyshaFilesRepository.save(toDyshaFileEntity(dyshaFile));
    }

    private DyshaFileEntity toDyshaFileEntity(DyshaFile d) {
        return new DyshaFileEntity(d.getFromUserId(), d.getForUserId(),d.getTableName(), d.getFileType(), d.getName(), d.getData());
    }

    @Override
    public DyshaFile findByName(String fileName) {
        return toDyshaFile(daoDyshaFilesRepository.findByName(fileName));
    }

    @Override
    public List<DyshaFile> findAllByUserId(Long forId) {
        return toDyshaFileList(daoDyshaFilesRepository.findAllByForUserId(forId));
    }

    @Override
    public DyshaFile findById(Long fileId) {
        return toDyshaFile(daoDyshaFilesRepository.findById(fileId).get());
    }

    @Override
    public DyshaFile findByUniqueName(String uniqueName) {
        List<DyshaFile> dyshaIconList = toDyshaFileList(daoDyshaFilesRepository.findAllByTableName("DyshaJobs_Logo"));
        int size = dyshaIconList.size();
        return size > 0 ? dyshaIconList.get(size - 1): null;
    }

    @Override
    public void deleteById(Long fileId) {
        daoDyshaFilesRepository.deleteById(fileId);
    }

    private List<DyshaFile> toDyshaFileList(Iterable<DyshaFileEntity> allForId) {
        List<DyshaFile> filesForId = new ArrayList<>();
        allForId.forEach(e -> filesForId.add(toDyshaFile(e)));
        return filesForId;
    }

    private DyshaFile toDyshaFile(DyshaFileEntity e) {
        return new DyshaFile(e.getId(), e.getFromUserId(), e.getForUserId(), e.getTableName(), e.getFileType(), e.getName(), e.getData());
    }
}
