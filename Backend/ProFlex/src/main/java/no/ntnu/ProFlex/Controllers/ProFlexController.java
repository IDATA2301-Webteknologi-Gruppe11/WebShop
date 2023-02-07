package no.ntnu.ProFlex.Controllers;
import no.ntnu.ProFlex.Controllers.ProductController;
import no.ntnu.ProFlex.Products.ProductList;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the Rest Controller class for the ProFlex WebSite API
 *
 * @author Ole Kristian Dvergsdal
 * @author Mikkel
 * @author Håvard Vestbø
 * @author Christian
 * @version 1.0
 */
@RestController
public class ProFlexController {

    //Controllers
    ProductController productController = new ProductController();

    //data
    ProductList productList = new ProductList();

    /**
     * Starts the proFlexController
     */
    public void proFlexController() {
        initializeData();
    }

    /**
     * Initilize data that is used in the controller.
     */
    private void initializeData() {
        productController.setProductList(this.productList);
    }

    /**
     * Returns the productlist
     * @return productlist
     */
    public ProductList getProductList() {
        return this.productList;
    }
}
