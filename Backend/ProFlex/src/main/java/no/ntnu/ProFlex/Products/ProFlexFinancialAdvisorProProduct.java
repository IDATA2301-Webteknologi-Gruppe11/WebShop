package no.ntnu.ProFlex.Products;

/**
 * Legal ProFlex Financial Advisor Pro Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
public class ProFlexFinancialAdvisorProProduct extends Product{

    /**
     * Creates a ProFlex Finanical Advisor Pro Product.
     */
    protected ProFlexFinancialAdvisorProProduct() throws IllegalArgumentException {
        super("ProFlex Financial Advisor Pro", 6, 6667, "finance, planning");
    }
}
