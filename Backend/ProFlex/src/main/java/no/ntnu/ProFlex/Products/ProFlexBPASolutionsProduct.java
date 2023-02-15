package no.ntnu.ProFlex.Products;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Legal ProFlex BPA Solutions Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "A product", title = "Pro Flex BPA Solutions")
public class ProFlexBPASolutionsProduct extends Product{

    /**
     * Creates a ProFlex BPA Solutions Product.
     * @throws IllegalArgumentException
     */
    public ProFlexBPASolutionsProduct() throws IllegalArgumentException {
        super("Proflex BPA Solutions", 2, 5000, "automation, processes");
    }

}
