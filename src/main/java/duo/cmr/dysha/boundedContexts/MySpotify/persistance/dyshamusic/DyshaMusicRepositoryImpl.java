package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshamusic;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.comment.DyshaComment;
import duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshamusic.DyshaMusic;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshacomment.DyshaCommentRepositoryImpl;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshaplaylist.DyshaPlaylistRepositoryImpl;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.likes.MusicLikesRepositoryImpl;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.ecoute.MusicViewsRepositoryImpl;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.database.appuser.AppUserRepositoryImpl;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.DyshaMusicRepository;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@AllArgsConstructor
public class DyshaMusicRepositoryImpl implements DyshaMusicRepository {
    private DaoDyhsaMusicRepository daoDyhsaMusicRepository;
    private AppUserRepositoryImpl appUserRepository;
    private DyshaFilesService dyshaFilesService;
    private MusicLikesRepositoryImpl likesRepository;
    private MusicViewsRepositoryImpl viewsRepository;
    private DyshaCommentRepositoryImpl commentRepository;
    private DyshaPlaylistRepositoryImpl playlistRepository;

    @Override
    public List<DyshaMusic> findAll() {
        return toDyshaMusicList(daoDyhsaMusicRepository.findAll());
    }

    @Override
    public void save(DyshaMusic music) {
        daoDyhsaMusicRepository.save(toDyshaMusicEntity(music));
    }

    @Override
    public DyshaMusic findByMusicName(String filename) {
        System.out.println(filename);
        return toDyshaMusic(daoDyhsaMusicRepository.findByMusicFileName(filename));
    }

    @Override
    public DyshaMusic findById(Long musicId) {
        return toDyshaMusic(daoDyhsaMusicRepository.findById(musicId).get());
    }

    // TODO: 23.05.2023 implementer une generation de playliste intelligeament en function de la music qui vinet detre selection
    @Override
    public List<DyshaMusic> trySomePlaylistBy(Long musicId) {
        return getRecommendedMusic(findById(musicId), findAll());
    }

    private DyshaMusicEntity toDyshaMusicEntity(DyshaMusic music) {
        return new DyshaMusicEntity(music.getTitle(), music.getDescription(), music.getMusicFileName(), music.getThumbnailFileName(), music.getPostedOn(), music.getUser().getId());
    }

    private List<DyshaMusic> toDyshaMusicList(Iterable<DyshaMusicEntity> all) {
        List<DyshaMusic> result = new ArrayList<>();
        all.forEach(e -> result.add(toDyshaMusic(e)));
        return result;
    }

    private DyshaMusic toDyshaMusic(DyshaMusicEntity e) {
        byte[] music = dyshaFilesService.findByName(e.getMusicFileName()).getData();
        byte[] thumbnailData = dyshaFilesService.findByName(e.getThumbnailFileName()).getData();
        Long totalLikes = likesRepository.countByMusicId(e.getId());
        Long totalViews = viewsRepository.countByMusicId(e.getId());
        List<DyshaComment> comments = commentRepository.findAllByMusicId(e.getId());
        return new DyshaMusic(e.getId(), e.getTitle(),e.getDescription(), e.getMusicFileName(), e.getThumbnailFileName(), music, thumbnailData, totalViews, totalLikes, comments, e.getPostedOn(), appUserRepository.findById(e.getUserId()));
    }

    public List<DyshaMusic> getRecommendedMusic(DyshaMusic m, List<DyshaMusic> lms) {
        // Créer un vecteur de fonctionnalités pour la musique m
        Map<String, Double> featuresM = extractFeatures(m);

        // Calculer la similarité cosinus entre la musique m et chaque musique de la liste lms
        List<MusicSimilarity> similarities = new ArrayList<>();
        for (DyshaMusic lm : lms) {
            Map<String, Double> featuresLM= extractFeatures(lm);
            double similarity = computeCosineSimilarity(featuresM, featuresLM);
            similarities.add(new MusicSimilarity(lm, similarity));
        }

        // Trier les musiques par ordre décroissant de similarité cosinus
        similarities.sort(Collections.reverseOrder());

        // Retourner la liste triée de musiques
        List<DyshaMusic> lmResult = new ArrayList<>();
        for (MusicSimilarity ms : similarities) {
            lmResult.add(ms.music());
        }
        return lmResult;
    }

    private Map<String, Double> extractFeatures(DyshaMusic m) {
        // Extraire les fonctionnalités de la musique m
        Map<String, Double> features = new HashMap<>();
        features.put("titre", getTitreWeight(m.getTitle()));
        features.put("artist", getArtistWeight(m.getUser().getFullName()));
        features.put("description", getDescriptionWeight(m.getDescription()));
        return features;
    }

    private double computeCosineSimilarity(Map<String, Double> features1, Map<String, Double> features2) {
        // Calculer la similarité cosinus entre les deux vecteurs de fonctionnalités
        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;
        for (String feature : features1.keySet()) {
            double value1 = features1.get(feature);
            double value2 = features2.getOrDefault(feature, 0.0);
            if (feature.equals("mfcc")) {
                dotProduct += value1 * value2;
                norm1 += value1 * value1;
                norm2 += value2 * value2;
            } else {
                dotProduct += value1 * value2 * 0.5; // Poids pour la fonctionnalité
                norm1 += value1 * value1 * 0.25; // Poids pour la fonctionnalité
                norm2 += value2 * value2 * 0.25; // Poids pour la fonctionnalité
            }
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    private double getTitreWeight(String genre) {
        // Retourne un poids pour le genre de musique
        // Par exemple, si l'utilisateur préfère le rock, le poids pour le genre rock sera plus élevé
        // Si le genre est inconnu ou non pertinent, le poids sera 0
        // La valeur de retour doit être comprise entre 0 et 1
        return 0.5; // Exemple de valeur de poids pour le genre
    }

    private double getArtistWeight(String artist) {
        // Retourne un poids pour l'artiste de la musique
        // Par exemple, si l'utilisateur préfère les Beatles, le poids pour les chansons des Beatles sera plus élevé
        // Si l'artiste est inconnu ou non pertinent, le poids sera 0
        // La valeur de retour doit être comprise entre 0 et 1
        return 0.1; // Exemple de valeur de poids pour l'artiste
    }

    private double getDescriptionWeight(String lyrics) {
        // Retourne un poids pour les paroles de la musique
        // Par exemple, si l'utilisateur préfère les chansons avec des paroles positives, le poids pour les paroles positives sera plus élevé
        // Si les paroles sont inconnues ou non pertinentes, le poids sera 0
        // La valeur de retour doit être comprise entre 0 et 1
        return 0.2; // Exemple de valeur de poids pour les paroles
    }

    private record MusicSimilarity(DyshaMusic music, double similarity) implements Comparable<MusicSimilarity> {

        @Override
            public int compareTo(MusicSimilarity o) {
                return Double.compare(similarity, o.similarity());
            }
        }
}
