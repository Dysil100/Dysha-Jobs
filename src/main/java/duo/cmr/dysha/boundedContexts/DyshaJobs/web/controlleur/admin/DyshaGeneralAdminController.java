package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.admin;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaJobService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.WorkerJobRelationService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.dyshafile.domain.DyshaFile;
import duo.cmr.dysha.boundedContexts.dyshafile.domain.FileInfos;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@AllArgsConstructor
@Controller
@AdminOnly
public class DyshaGeneralAdminController {
    private DyshaWorkerService dyshaWorkerService;
    private AppUserService appUserService;
    private WorkerJobRelationService workerJobRelationService;
    private DyshaFilesService dyshaFileService;
    private DyshaJobService dyshaJobService;


    @GetMapping("/dyshajobs/files/{tablename}/{entityid}/{filesType}")
    public String addFiles(@PathVariable String tablename, @PathVariable Long entityid, @ModelAttribute("user") @NotNull AppUser user, @NotNull Model model) {
        model.addAttribute("dyshaFile", new DyshaFile(user.getId(), entityid, tablename, dyshaFileService.defineFiletypeByTybleName(tablename), null, null));
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(user.getId())));
        return "addFiles";
    }

    @GetMapping("/dyshajobs/workers")
    public String employPage(@NotNull Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);
        model.addAttribute("dyshaworkers", dyshaWorkerService.finAll());
        return "dysha_jobs/dyshaworkers";
    }

    @GetMapping("/dyshajobs/dyshaPostulats")
    public String dyshaPostulats(@NotNull Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);;
        model.addAttribute("jobRelations", workerJobRelationService.findAll());
        return "dysha_jobs/dyshapostulats";
    }

    @PostMapping("/dyshajobs/validatejobrelation")
    public String validateJobRelation(@ModelAttribute("id") Long id, Model model) {
        workerJobRelationService.validateJobRelationById(id);
        return "redirect:/dyshajobs/dyshaPostulats";
    }
    @PostMapping("/dyshajobs/deletejobrelation")
    public String deleteJobRelation(@ModelAttribute("id") Long id, Model model) {
        workerJobRelationService.deleteJobRelationById(id);
        return "redirect:/dyshajobs/dyshaPostulats";
    }

    @GetMapping("/dyshajobs/mesdocuments/{workerId}")
    public String workerDocuments(@PathVariable("workerId") Long workerId, @ModelAttribute("globalUser") GlobalAppUser user, @org.jetbrains.annotations.NotNull Model model) {
        Long thisUserId = dyshaWorkerService.findById(workerId).getUserId();
        List<DyshaFile> allByEntityId = dyshaFileService.findAllByUserId(thisUserId);
        model.addAttribute("dyshaFiles", allByEntityId);
        model.addAttribute("globalUser", user);
        return "addFiles";
    }

    @PostMapping("/dyshajobs/delete/file")
    public String deleteFile(@ModelAttribute("fileId") Long fileId, @ModelAttribute("globalUser") @NotNull GlobalAppUser user) {
        // Récupérer le fichier à partir de l'ID
        DyshaFile fileById = dyshaFileService.findFileById(fileId);
        dyshaFileService.deleteById(fileId);
        return "redirect:/dyshajobs/mesdocuments/" + fileById.getForUserId();
    }

    @GetMapping("/dyshajobs/dyshaprofil/{workerId}")
    public String showProfilDetails(@PathVariable("workerId") Long workerId, Authentication authentication, @NotNull Model model, @ModelAttribute("user") AppUser user) {
        Long thisUserId = dyshaWorkerService.findById(workerId).getUserId();
        model.addAttribute("userHasCuriculumVitae", dyshaFileService.cVExistByEntityId(thisUserId));
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findById(workerId)));
        return "dysha_jobs/dyshaprofil";
    }

    @GetMapping("/dyshajobs/mesDyshaJobs/{userId}")
    public String dyshaWorkerJobsListe(@NotNull Model model, @ModelAttribute("user") AppUser user, @PathVariable("userId") Long userId) {
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(userId)));;
        return "dysha_jobs/dyshajobliste";
    }

    @GetMapping("/dyshajobs/createDyshaJob")
    public String showCreateJobForm(@NotNull Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("newJob", true);
        model.addAttribute("globalUser", user);
        model.addAttribute("dyshaJob", new DyshaJob(null, null, null, null, null, null));
        return "dysha_jobs/newdyshajob";
    }

    @PostMapping("/dyshajobs/createDyshaJob")
    public String createJob(Model model, @ModelAttribute("dyshaJob") DyshaJob dyshaJob, @RequestParam("jobImages") List<MultipartFile> jobImages, @NotNull BindingResult result , @ModelAttribute("globalUser") GlobalAppUser user) {
        if (!dyshaJobService.validates(dyshaJob)) {
            model.addAttribute("dyshaJob", dyshaJob);
            result.rejectValue("title", "dyshaJob.type.invalid", "Vous avez entré des caractere non authorizé! I don't manage some special caratere. ");
        }
        if (result.hasErrors()) {
            model.addAttribute("dyshaJob", dyshaJob);
            return "dysha_jobs/newdyshajob";
        }
        List<String> images = dyshaFileService.saveAll(new FileInfos(user.getUser().getId(), user.getUser().getId(), dyshaJob.tableName(), "image/*"), jobImages);
        dyshaJob.setUserId(user.getUser().getId());
        dyshaJob.setImages(images);
        dyshaJob.setPostedDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        dyshaJobService.save(dyshaJob);
        return "redirect:/dyshajobs";
    }

    @GetMapping("/dyshajobs/updateDyhajob/{jobId}")
    public String showUpdateJobForm(@NotNull Model model, @ModelAttribute("globalUser") GlobalAppUser user, @PathVariable("jobId") Long jobId) {
        model.addAttribute("update", true);
        model.addAttribute("globalUser", user);
        model.addAttribute("udatedDyshaJob", dyshaJobService.getJobById(jobId));
        return "dysha_jobs/newdyshajob";
    }
    @PostMapping("/dyshajobs/updateDyhajob")
    public String updateJob(Model model, @ModelAttribute("udatedDyshaJob") DyshaJob dyshaJob, @RequestParam("jobImages") List<MultipartFile> jobImages, @NotNull BindingResult result , @ModelAttribute("globalUser") GlobalAppUser user) {
        if (!dyshaJobService.validates(dyshaJob)) {
            model.addAttribute("udatedDyshaJob", dyshaJob);
            result.rejectValue("title", "dyshaJob.type.invalid", "Vous avez entré des caractere non authorizé! I don't manage some special caratere. ");
        }
        if (result.hasErrors()) {
            model.addAttribute("udatedDyshaJob", dyshaJob);
            return "dysha_jobs/newdyshajob";
        }
        List<String> images1 = dyshaFileService.saveAll(new FileInfos(user.getUser().getId(), user.getUser().getId(), dyshaJob.tableName(), "image/*"), jobImages);
        dyshaJob.setUserId(user.getUser().getId());
        List<String> images = dyshaJob.getImages();
        images.addAll(images1);
        dyshaJob.setImages(images);
        dyshaJob.setPostedDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        dyshaJobService.update(dyshaJob);
        return "redirect:/dyshajobs";
    }

    @ModelAttribute("user")
    AppUser currentUser(@NotNull Principal user) {
        return appUserService.findByEmail(user.getName());
    }

    @ModelAttribute("globalUser")
    GlobalAppUser currentGlobalUser(@NotNull Principal user) {
        AppUser appUser = appUserService.findByEmail(user.getName());
        return new GlobalAppUser(appUser, dyshaWorkerService.findByUserId(appUser.getId()));
    }
}
