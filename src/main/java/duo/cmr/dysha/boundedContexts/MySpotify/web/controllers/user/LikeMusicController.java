package duo.cmr.dysha.boundedContexts.MySpotify.web.controllers.user;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.playlistmusic.PlaylistMusic;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices.DyshaMusicService;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices.DyshaPlaylistService;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices.PlaylistMusicService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.MySpotify.domain.musiclikes.MusicLikes;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices.MusicLikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class LikeMusicController {
    private DyshaMusicService musicService;
    private MusicLikesService likesService;
    private DyshaPlaylistService playlistService;
    private PlaylistMusicService playlistMusicService;
    private AppUserService appUserService;

    @GetMapping("/likedyshamusic/{musicId}")
    public String likeVideo(@PathVariable("musicId") Long musicId, @ModelAttribute("user") AppUser user) {
       likesService.saveLike(new MusicLikes(musicId, user.getId(), "like"));
       Long playlistId = playlistService.findByUserIdAndPlaylistName(user.getId(), "favoris").getId();
       playlistMusicService.save(new PlaylistMusic(playlistId, musicId));
       return "redirect:/dyshamusic/" + musicService.findById(musicId).getMusicFileName();
    }

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

}
