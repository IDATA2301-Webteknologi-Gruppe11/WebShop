package no.ntnu.ProFlex.Products;

/**
 * Legal ProFlex BPA Solutions Product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
public class ProflexBPASolutionsProduct extends Product{

    /**
     * Creates a ProFlex BPA Solutions Product.
     * @throws IllegalArgumentException
     */
    public ProflexBPASolutionsProduct() throws IllegalArgumentException {
        super("Proflex BPA Solutions", 2, 5000, "automation, processes");
    }

}
