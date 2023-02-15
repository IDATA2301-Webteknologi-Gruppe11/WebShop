package no.ntnu.proflex.products;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Legal ProFlex Accounting Solutions Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "A product", title = "Pro Flex Accounting Solutions")
public class ProFlexAccountingSolutionsProduct extends Product {

    /**
     * Create a ProFlex Accounting Solutions Product.
     */
    public ProFlexAccountingSolutionsProduct() throws IllegalArgumentException {
        super("ProFlex Accounting Solutions", 4, 500, "accounting, legal, tax, finance");
    }
}
