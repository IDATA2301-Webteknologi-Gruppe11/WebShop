package no.ntnu.ProFlex.Products;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Legal ProFlex Tax Solutions Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "A product", title = "Pro Flex Tax Solutions")
public class ProFlexTaxSolutionsProduct extends Product{

    /**
     * Create ProFlex Tax Solutions Product.
     */
    public ProFlexTaxSolutionsProduct() throws IllegalArgumentException {
        super("ProFlex Tax Solutions", 3, 200, "tax, legal, accounting");
    }
}
