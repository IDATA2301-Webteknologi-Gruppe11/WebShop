package no.ntnu.ProFlex.Products;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Legal ProFlex Financial Advisor Pro Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "A product", title = "Pr Flex Financial Advisor")
public class ProFlexFinancialAdvisorProProduct extends Product{

    /**
     * Creates a ProFlex Finanical Advisor Pro Product.
     */
    protected ProFlexFinancialAdvisorProProduct() throws IllegalArgumentException {
        super("ProFlex Financial Advisor Pro", 6, 6667, "finance, planning");
    }
}
