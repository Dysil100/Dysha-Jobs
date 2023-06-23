package duo.cmr.dysha.boundedContexts.dyshafile.web.services;

import duo.cmr.dysha.boundedContexts.dyshafile.domain.DyshaFile;
import duo.cmr.dysha.boundedContexts.dyshafile.domain.FileInfos;
import duo.cmr.dysha.boundedContexts.dyshafile.persistance.FileTypeService;
import duo.cmr.dysha.boundedContexts.dyshafile.web.interfaces.DyshaFilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DyshaFilesService {
    private DyshaFilesRepository dyshaFilesRepository;
    private FileTypeService fileTypeService;



    public DyshaFile findByName(String filename) {
        return dyshaFilesRepository.findByName(filename);
    }

    public List<String> saveAll(FileInfos fileInfos, List<MultipartFile> images)  {
        return images.stream().map(e -> save(fileInfos, e)).toList();
    }

    public String save(FileInfos fInfos, MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DyshaFile dyshaFile = new DyshaFile(fInfos.fromId(), fInfos.forId(), fInfos.tableNme(), fInfos.fileType(), fileName, bytes);
        dyshaFilesRepository.saveFile(dyshaFile);
        return fileName;
    }
    public List<DyshaFile> findAllByUserId(Long forId) {
        return dyshaFilesRepository.findAllByUserId(forId);
    }

    public DyshaFile findFileById(Long fileId) {
        return dyshaFilesRepository.findById(fileId);
    }

    public DyshaFile findByUniqueName(String uniqueName) {
        return dyshaFilesRepository.findByUniqueName(uniqueName);
    }

    public String defineFiletypeByTybleName(String tablename) {
        return fileTypeService.defineFiletypeByTybleName(tablename);
    }

    public void deleteById(Long fileId) {
        dyshaFilesRepository.deleteById(fileId);
    }

    public Boolean cVExistByEntityId(Long foUserId) {
        return dyshaFilesRepository.findAllByUserId(foUserId).stream().map(DyshaFile::getTableName).toList().contains("CV_document");
    }

    public byte[] getDataBytes(MultipartFile file) {
        return fileTypeService.getDataBytes((Part) file);
    }

    public String determineFileType(byte[] filesDataBytes) {
        return fileTypeService.determineFileType(filesDataBytes);
    }

    public String findProfilImageFor(Long userId) {
        List<DyshaFile> profilPhotoImages = dyshaFilesRepository.findAllByUserId(userId).stream().filter(e -> e.getTableName().equals("Profil_photo_image")).toList();
        return profilPhotoImages.isEmpty() ? "null" : profilPhotoImages.get(0).getName();
    }
}
