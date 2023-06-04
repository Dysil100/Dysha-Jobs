package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaJobValidations;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshafile.FileTypeService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class DyshaFileService {
    private FileTypeService fileTypeService;
    private DyshaFileRepository dyshaFileRepository;

    public void save(DyshaFile dyshaFile) {
        dyshaFileRepository.save(dyshaFile);
    }

    public List<DyshaFile> findAllByUserId(Long entityId) {
        return dyshaFileRepository.findAllByUserId(entityId);
    }

    public boolean cVExistByEntityId(Long entityId) {
        return !dyshaFileRepository.findLastByTableNameAndEntityIdAndFileType("CV_document", entityId, "pdf").isBlank();
    }

    public DyshaFile findFileById(Long fileId) {
        return dyshaFileRepository.findFieById(fileId);
    }

    public void deleteById(Long fileId) {
        dyshaFileRepository.deleteById(fileId);
    }

    public DyshaJobValidations getValidationsFor(Long entityId) {
        return dyshaFileRepository.getValidationFor(entityId);
    }

    public List<DyshaFile> findAllByEntityId(Long entityId) {
        return dyshaFileRepository.findAllByEntityId(entityId);
    }

    public String defineFiletypeByTybleName(String tablename) {
        return fileTypeService.defineFiletypeByTybleName(tablename);
    }

    public byte[] getDataBytes(Part file) {
        return fileTypeService.getDataBytes(file);
    }

    public String determineFileType(byte[] filesDataBytes) {
        return fileTypeService.determineFileType(filesDataBytes);
    }

    public String getAcceptedType(String tablename) {
      return Objects.equals(fileTypeService.defineFiletypeByTybleName(tablename), "image") ? "image/*" : ".pdf";
    }

    public DyshaFile findByUniqueName(String uniqueName) {
        return dyshaFileRepository.findByUniqueName(uniqueName);
    }
}
