package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.Product;
import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.SearcedListResult;
import duo.cmr.dysha.boundedContexts.GoodDeals.domain.oders.FilterForm;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchProductService {

    public SearcedListResult searchProductsByExpr(List<Product> all, String query, FilterForm filterForm) {
        //retourner tous les produits si les ya aucune query particuliere de recherche
        if (null == query || query.isBlank()) return new SearcedListResult(new ArrayList<>(), filterProducts(filterForm,all));

        Map<Product, Integer> scores = new HashMap<>(); // stocker le score pour chaque produit
        List<Product> restList = new ArrayList<>();

        updateScoresAndRestlist(all, query, scores, restList);

        // trier les Products par ordre décroissant de score
        List<Product> resultat = new ArrayList<>(scores.keySet());
        resultat.sort((p1, p2) -> scores.get(p2) - scores.get(p1));
        // filtrere les resultats et la liste des reste de produit
        List<Product> products = filterProducts(filterForm, resultat);
        List<Product> rest = filterProducts(filterForm, restList);
        return new SearcedListResult(products, rest);
    }
    public List<Product> filterProducts(FilterForm filterForm, List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                int relevance1 = calculateRelevance(filterForm, p1);
                int relevance2 = calculateRelevance(filterForm, p2);

                // Tri en ordre décroissant de pertinence
                return Integer.compare(relevance2, relevance1);
            }
        });

        return products;
    }

    private int calculateRelevance(FilterForm filterForm, Product product) {
        int relevance = 0;

        // Comparaison des critères de filtrage et du produit
        if (filterForm.getFcategorie() != null && filterForm.getFcategorie().equals(product.getCathegorie())) {
            relevance += 1;
        }

        if (filterForm.getPrixMin() != null && filterForm.getPrixMin() <= product.getPrix()) {
            relevance += 1;
        }

        if (filterForm.getPrixMax() != null && filterForm.getPrixMax() >= product.getPrix()) {
            relevance += 1;
        }

        if (filterForm.getFregion() != null && filterForm.getFregion().equals(product.getRegion())) {
            relevance += 1;
        }

        if (filterForm.getFville() != null && filterForm.getFville().equals(product.getVille())) {
            relevance += 1;
        }

        if (filterForm.isFvente() == product.getVente()) {
            relevance += 1;
        }

        return relevance;
    }

    private void updateScoresAndRestlist(List<Product> all, String query, Map<Product, Integer> scores, List<Product> restList) {
        // parcourir tous les produits et calculer le score
        for (Product p : all) {
            int score = 0;
            if (p.getTitre().contains(query)) {
                score += 5; // titre est important, mais moins que le prix, on ajoute un score de 3
            }
            if (p.getDescription().contains(query)) {
                score += 3; // description est moins important que le titre, on ajoute un score de 1
            }
            if (p.getRegion().contains(query)) {
                score += 1; // ville est plus important que description, on ajoute un score de 2
            }
            if (p.getUserEmail().contains(query)) {
                score += 4; // createur est moins important que date de publication, on ajoute un score de 1
            }
            if (p.getQuartier().contains(query)) {
                score += 2; // quartier est plus important que createur, on ajoute un score de 2
            }

            if ((score > 0)) {
                scores.put(p, score);
            } else {
                restList.add(p);
            }
            ; // ajouter le score seulement si le mot-clé est présent
        }
    }

}
