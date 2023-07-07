package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.dyshafile.domain.DyshaFile;
import duo.cmr.dysha.boundedContexts.dyshafile.domain.FileInfos;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
public class DyshaFileController {

    private AppUserService appUserService;
    private DyshaFilesService dyshaFileService;
    private DyshaWorkerService dyshaWorkerService;

    @GetMapping("/dyshajobs/mesdocuments")
    public String mesDocuments(@ModelAttribute("globalUser") GlobalAppUser user, Model model) {
        model.addAttribute("dyshaFiles", dyshaFileService.findAllByUserId(user.getUser().getId()));
        model.addAttribute("globalUser", user);
        return "addFiles";
    }

    @GetMapping("/dyshajobs/files/{tablename}")
    public String addFilesUser(@PathVariable String tablename, @ModelAttribute("user") AppUser user, Model model) {
        DyshaWorker workerByUserId = dyshaWorkerService.findByUserId(user.getId());
        model.addAttribute("dyshaFile", new DyshaFile(user.getId(), workerByUserId.getId(), tablename, dyshaFileService.defineFiletypeByTybleName(tablename),null, null));
        model.addAttribute("globalUser", new GlobalAppUser(user, workerByUserId));
        return "addFiles";
    }

    @PostMapping("/dyshajobs/files")
    public String addFiles(@RequestParam("files") MultipartFile file,
                           @ModelAttribute("dyshaFile") @Valid DyshaFile dyshaFile, BindingResult result, Model model, @ModelAttribute("globalUser") GlobalAppUser user) throws IOException {

        //verifier la taille du fichier
        if (file.getSize() > 5000000) { // le fichier doit peser environ 0,5 Megga Octet
            result.rejectValue("fileType", "file.type.invalid", "Le fichier doit peser environ 0.5 Megga Octets. <br> Veuillez compresser votre image svp.");
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("dyshaFile", dyshaFile);

            return "addFiles";
        }

        // Lire le contenu de la photo dans un tableau d'octets
        byte[] filesDataBytes = dyshaFileService.getDataBytes(file);
        String definedFiletype = dyshaFileService.defineFiletypeByTybleName(dyshaFile.getTableName());
        String determineFileType = dyshaFileService.determineFileType(filesDataBytes);
        // Vérifier si le fichier uploadé est un fichier approprié
        if (!definedFiletype.equalsIgnoreCase(determineFileType)) {
            result.rejectValue("fileType", "file.type.invalid", "Le fichier doit être de type " + definedFiletype);
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("dyshaFile", dyshaFile);
            return "addFiles";
        }

        // Créer un fichier Photo et Enregistrer la photo
        Long entityId = dyshaFile.getForUserId();
        dyshaFileService.saveAll(new FileInfos(user.getUser().getId(), entityId == null ? user.getUser().getId(): entityId, dyshaFile.getTableName(), determineFileType), List.of(file));
        return "redirect:/dyshajobs";
    }

    @GetMapping("/dyshajobs/download/file/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId, @ModelAttribute("globalUser") GlobalAppUser user) {
        // Récupérer le fichier à partir de l'ID
        DyshaFile dyshaFile = dyshaFileService.findFileById(fileId);
        // Vérifier si le fichier existe
        if (dyshaFile == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(dyshaFile.getData(), dyshaFile.buildHeadersFor(user.getUser().getFirstName()), HttpStatus.OK);
    }

    @GetMapping("/files/{uniqueName}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String uniqueName) {
        System.out.println(uniqueName);
        DyshaFile file = dyshaFileService.findByUniqueName(uniqueName);
        System.out.println(file.getName());
        if (file != null) {
            ByteArrayResource resource = new ByteArrayResource(file.getData());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + uniqueName + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

    @ModelAttribute("globalUser")
    GlobalAppUser currentGlobalUser(Principal user) {
        AppUser appUser = appUserService.findByEmail(user.getName());
        return new GlobalAppUser(appUser, dyshaWorkerService.findByUserId(appUser.getId()));
    }
}
