package no.ntnu.ProFlex.Products;

/**
 * Legal ProFlex Tax Solutions Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
public class ProFlexTaxSolutionsProduct extends Product{

    /**
     * Create ProFlex Tax Solutions Product.
     */
    public ProFlexTaxSolutionsProduct() throws IllegalArgumentException {
        super("ProFlex Tax Solutions", 3, 200, "tax, legal, accounting");
    }
}
