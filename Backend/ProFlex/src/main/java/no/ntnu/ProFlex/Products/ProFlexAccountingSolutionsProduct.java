package no.ntnu.ProFlex.Products;

/**
 * Legal ProFlex Accounting Solutions Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
public class ProFlexAccountingSolutionsProduct extends Product {

    /**
     * Create a ProFlex Accounting Solutions Product.
     */
    public ProFlexAccountingSolutionsProduct() throws IllegalArgumentException {
        super("ProFlex Accounting Solutions", 4, 500, "accounting, legal, tax, finance");
    }
}
