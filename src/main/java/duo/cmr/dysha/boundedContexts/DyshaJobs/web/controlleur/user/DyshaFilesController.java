package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaFileService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@AllArgsConstructor
@Controller
public class DyshaFilesController {

    private AppUserService appUserService;
    private DyshaFileService dyshaFileService;
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
        model.addAttribute("dyshaFile", new DyshaFile(user.getId(), workerByUserId.getId(), tablename, dyshaFileService.defineFiletypeByTybleName(tablename), null));
        model.addAttribute("filesTypeInput", dyshaFileService.getAcceptedType(tablename));
        model.addAttribute("globalUser", new GlobalAppUser(user, workerByUserId));
        return "addFiles";
    }

    @PostMapping("/dyshajobs/files")
    public String addFiles(@RequestParam("file") Part file,
                           @ModelAttribute("dyshaFile") @Valid DyshaFile dyshaFile, BindingResult result, Model model, @ModelAttribute("globalUser") GlobalAppUser user) throws IOException {
        System.out.println(dyshaFile);

        //verifier la taille du fichier
        if (file.getSize() > 5000000) { // le fichier doit peser environ 0,5 Megga Octet
            result.rejectValue("fileType", "file.type.invalid", "Le fichier doit peser environ 0.5 Megga Octets. <br> Veuillez compresser votre image svp.");
            model.addAttribute("errors", result.getAllErrors());
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
            return "addFiles";
        }
        // Créer un fichier Photo et Enregistrer la photo
        Long entityId = dyshaFile.getEntityId();
        dyshaFileService.save(new DyshaFile(dyshaFile.getUserId(), entityId == null ? user.getUser().getId(): entityId, dyshaFile.getTableName(), determineFileType, filesDataBytes));
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
        return new ResponseEntity<>(dyshaFile.getFile(), dyshaFile.buildHeadersFor(user.getUser().getFirstName()), HttpStatus.OK);
    }

    @GetMapping("/files/{uniqueName}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String uniqueName) {
        DyshaFile file = dyshaFileService.findByUniqueName(uniqueName);
        if (file != null) {
            ByteArrayResource resource = new ByteArrayResource(file.getFile());
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
