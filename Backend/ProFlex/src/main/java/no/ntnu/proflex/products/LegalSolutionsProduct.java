package no.ntnu.proflex.products;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Legal Solutions Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "A product", title = "Legal Solutions")
public class LegalSolutionsProduct extends Product {

    /**
     * Creates a Legal Solutions product
     */
    public LegalSolutionsProduct() throws IllegalArgumentException {
        super("Legal Solutions", 5, 9999, "legal");
    }
}
