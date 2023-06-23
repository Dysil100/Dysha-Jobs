package duo.cmr.dysha.boundedContexts.MySpotify.web.controllers.user;


import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshafiles.DyshaFiles;
import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshamusic.DyshaMusic;
import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshamusic.MusicForm;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices.DyshaMusicService;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices.MusicLikesService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Controller
public class DyshaMusicController {

    private MusicLikesService likesService;
    private DyshaMusicService musicService;
    private DyshaFilesService dyshaFilesService;
    private AppUserService appUserService;
    @GetMapping("/dyshamusicindex")
    public String homePage(Model model) {
        List<DyshaMusic> musics = musicService.getMusics();
        model.addAttribute("dyshamusics", musics);
        return "dysha-music/DyshaMusicIndex";
    }

    @GetMapping("/newdyshamusic/upload")
    public String addDyshaMusic(Model model) {
        model.addAttribute("musicForm", new MusicForm());
        return "dysha-music/dyshauploadmusic";
    }

    @PostMapping("/newdyshamusic/upload")
    public String addDyshaMusic(@ModelAttribute MusicForm musicForm,
                                @RequestParam("musicFile") MultipartFile musicFile,
                                @RequestParam("thumbnailFile") MultipartFile thumbnailFile, @ModelAttribute("user") AppUser user) throws IOException {

        if (musicFile.isEmpty() || thumbnailFile.isEmpty()) {
            // Gérer le cas où les fichiers sont manquants
            return "error";
        } try {
            String musicFileName = dyshaFilesService.save(new FileInfos(user.getId(), user.getId(), "dysha-music", "Audio-mp3"), musicFile);
            String thumbnailFileName = dyshaFilesService.save(new FileInfos(user.getId(), user.getId(), "music-thumnail", "Image"), thumbnailFile);
            DyshaMusic music = new DyshaMusic(musicForm.getTitle(), musicForm.getDescription(), musicFileName, thumbnailFileName, 0L, 0L, LocalDateTime.now(), appUserService.findById(user.getId()));
            musicService.saveMusic(music);
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/dyshamusicindex";
    }

    @GetMapping("/dysha_files/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        DyshaFile file = dyshaFilesService.findByName(fileName);
        if (file != null) {
            ByteArrayResource resource = new ByteArrayResource(file.getData());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dyshamusic/{musicFileName}")
    public String playMusic(@PathVariable String musicFileName, Model model, @ModelAttribute("user") AppUser user) {
        DyshaMusic music = musicService.findByMusicFileName(musicFileName);
        List<DyshaMusic> musics = musicService.trySomePlaylistBy(music.getId());
        model.addAttribute("dyshamusic", music);
        if (!musics.isEmpty()) {
            model.addAttribute("next", musics.get(0).getMusicFileName());
        }
        model.addAttribute("liked", likesService.existsByMusicIdAndUserId(music.getId(), user.getId()));
        model.addAttribute("playlist", musicService.getMusics());
        return "dysha-music/spotifylikepage";
    }
    @GetMapping("/getmusic/{musicId}")
    public String playMusic(@PathVariable Long musicId, Model model, @ModelAttribute("user") AppUser user) {
        System.out.println("hi");
        return "redirect:dyshamusic/" + musicService.findById(musicId).getMusicFileName();
    }

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

}
