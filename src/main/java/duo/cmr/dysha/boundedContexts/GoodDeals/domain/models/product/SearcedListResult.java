package duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class SearcedListResult {
    private List<Product> products;
    private List<Product> restlProduct;
}
