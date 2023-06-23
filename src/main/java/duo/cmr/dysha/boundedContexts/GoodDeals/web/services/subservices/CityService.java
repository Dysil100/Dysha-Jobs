package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Service
public class CityService {
      private final Map<String, List<String>> regionCitiesMap = new HashMap<>();

    public CityService() {
        // Région du Centre
        List<String> centreCities = new ArrayList<>();
        centreCities.add("Yaoundé");
        centreCities.add("Ntui");
        centreCities.add("Mbalmayo");
        centreCities.add("Obala");
        centreCities.add("Eseka");
        regionCitiesMap.put("Centre", centreCities);

        // Région du Littoral
        List<String> littoralCities = new ArrayList<>();
        littoralCities.add("Douala");
        littoralCities.add("Kribi");
        littoralCities.add("Limbe");
        littoralCities.add("Tiko");
        littoralCities.add("Edea");
        regionCitiesMap.put("Littoral", littoralCities);

        // Région de l'Ouest
        List<String> ouestCities = new ArrayList<>();
        ouestCities.add("Bafoussam");
        ouestCities.add("Bamenda");
        ouestCities.add("Dschang");
        ouestCities.add("Foumban");
        ouestCities.add("Mbouda");
        regionCitiesMap.put("Ouest", ouestCities);

        // Région du Nord-Ouest
        List<String> nordOuestCities = new ArrayList<>();
        nordOuestCities.add("Bamenda");
        nordOuestCities.add("Bafut");
        nordOuestCities.add("Kumbo");
        nordOuestCities.add("Ndop");
        nordOuestCities.add("Nkambe");
        regionCitiesMap.put("Nord-Ouest", nordOuestCities);

        // Région du Sud-Ouest
        List<String> sudOuestCities = new ArrayList<>();
        sudOuestCities.add("Buea");
        sudOuestCities.add("Limbe");
        sudOuestCities.add("Kumba");
        sudOuestCities.add("Mamfe");
        sudOuestCities.add("Mutengene");
        regionCitiesMap.put("Sud-Ouest", sudOuestCities);

        // Région de l'Est
        List<String> estCities = new ArrayList<>();
        estCities.add("Bertoua");
        estCities.add("Abong-Mbang");
        estCities.add("Batouri");
        estCities.add("Lomié");
        estCities.add("Doumé");
        regionCitiesMap.put("Est", estCities);

        // Région de l'Adamaoua
        List<String> adamaouaCities = new ArrayList<>();
        adamaouaCities.add("Ngaoundéré");
        adamaouaCities.add("Meiganga");
        adamaouaCities.add("Tibati");
        adamaouaCities.add("Banyo");
        adamaouaCities.add("Figuif");
        regionCitiesMap.put("Adamaoua", adamaouaCities);

        // Région du Nord
        List<String> nordCities = new ArrayList<>();
        nordCities.add("Garoua");
        nordCities.add("Guider");
        nordCities.add("Poli");
        nordCities.add("Lagdo");
        nordCities.add("Rey-Bouba");
        regionCitiesMap.put("Nord", nordCities);

        // Région du Sud
        List<String> sudCities = new ArrayList<>();
        sudCities.add("Kribi");
        sudCities.add("Ebolowa");
        sudCities.add("Sangmélima");
        sudCities.add("Meyomessala");
        sudCities.add("Moloundou");
        regionCitiesMap.put("Sud", sudCities);

        // Région de l'Extrême-Nord
        List<String> extremeNordCities = new ArrayList<>();
        extremeNordCities.add("Maroua");
        extremeNordCities.add("Mokolo");
        extremeNordCities.add("Kousséri");
        extremeNordCities.add("Mora");
        extremeNordCities.add("Zina");
        regionCitiesMap.put("Extrême-Nord", extremeNordCities);
    }

    public List<String> getRegions() {
        return getRegionCitiesMap().keySet().stream().toList();
    }

    public List<String> getCitiesOf(String region) {
        return getRegionCitiesMap().get(region);
    }
}
