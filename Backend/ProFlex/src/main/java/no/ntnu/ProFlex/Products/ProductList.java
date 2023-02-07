package no.ntnu.ProFlex.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is holding on to the products in a list
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
public class ProductList {

    private Product onlineSchedulingSoftware = new OnlineSchedulingSoftwareProduct();
    private Product legalSolutions = new LegalSolutionsProduct();
    private Product proFlexAccountingSolutions = new ProFlexAccountingSolutionsProduct();
    private Product proFlexBPASolutions = new ProflexBPASolutionsProduct();
    private Product proFlexFinancialAdvisor = new OnlineSchedulingSoftwareProduct();
    private Product proFlexTaxSolutions = new ProFlexTaxSolutionsProduct();
    private List<Product> productList = new ArrayList<>();

    public ProductList() {
        initialize();
    }

    /**
     * Initialize te data for the product list
     */
    private void initialize() {
        this.productList.add(this.onlineSchedulingSoftware);
        this.productList.add(this.legalSolutions);
        this.productList.add(this.proFlexAccountingSolutions);
        this.productList.add(this.proFlexBPASolutions);
        this.productList.add(this.proFlexFinancialAdvisor);
        this.productList.add(this.proFlexTaxSolutions);
    }

    /**
     * Returns the product of a given ID.
     * @param id the ID of the product you want to check.
     * @return the product of the given ID.
     */
    public Product getProductFromAGivenID(int id) {
        Product productFound = null;
        for(int i = 0; i < productList.size()-1; i++) {
            if(this.productList.get(i).getId() == id) {
                productFound = this.productList.get(i);
            }
        }
        return productFound;
    }

    /**
     * Returns all the products in the product list
     * @return all products
     */
    public List<Product> getProducts() {
        return this.productList;
    }

    /**
     * Returns all the ID form the existing products
     * @return all ID from products
     */
    public List<Integer> getAllIdFromProductList() {
        List<Integer> ids = new ArrayList<>();
        for(int i = 0; i < productList.size(); i++) {
            ids.add(productList.get(i).getId());
        }
        return ids;
    }

    /**
     * Add Product to the product list.
     * @param product the product that is geting added to the list.
     */
    public void addProduct(Product product) {
        this.productList.add(product);
    }
}
