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

    private final Product onlineSchedulingSoftware = new Product("Online Scheduling Software", 1, 2000, "planning, scheduling");
    private final Product legalSolutions = new Product("Proflex BPA Solutions", 2, 5000, "automation, processes");
    private final Product proFlexAccountingSolutions = new Product("ProFlex Tax Solutions", 3, 200, "tax, legal, accounting");
    private final Product proFlexBPASolutions = new Product(" ProFlex Accounting Solutions", 4, 500, "accounting, legal, tax, finance");
    private final Product proFlexFinancialAdvisor = new Product("Legal Solutions", 5, 9999, "legal");
    private final Product proFlexTaxSolutions = new Product("ProFlex Financial Advisor Pro", 6, 6667, "finance, planning");
    private final List<Product> productList = new ArrayList<>();

    public ProductList() {

    }

    /**
     * Initialize te data for the product list
     */
    public void initialize() {
        this.productList.add(this.proFlexFinancialAdvisor);
        this.productList.add(this.onlineSchedulingSoftware);
        this.productList.add(this.legalSolutions);
        this.productList.add(this.proFlexTaxSolutions);
        this.productList.add(this.proFlexAccountingSolutions);
        this.productList.add(this.proFlexBPASolutions);
    }

    /**
     * Merge sort algorithm that sorts the products in the production from low to high number baste of the product ID.
     * @param list the list that is getting sorted.
     */
    private static void mergeSort(List<Product> list) {
        int n = list.size();
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        List<Product> left = new ArrayList<>(list.subList(0, mid));
        List<Product> right = new ArrayList<>(list.subList(mid, n));

        mergeSort(left);
        mergeSort(right);
        merge(list, left, right);
    }

    /**
     * Merges the different sub arrays.
     * @param list the list that is sorted.
     * @param left left sub array.
     * @param right right sub array.
     */
    private static void merge(List<Product> list, List<Product> left, List<Product> right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int leftSize = left.size();
        int rightSize = right.size();

        while (i < leftSize && j < rightSize) {
            if (left.get(i).getId() <= right.get(j).getId()) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < leftSize) {
            list.set(k++, left.get(i++));
        }
        while (j < rightSize) {
            list.set(k++, right.get(j++));
        }
    }

    /**
     * Search for the index of a given product ID in the product list.
     * Returns -1 if id is not found.
     * @param list product list that is search
     * @param left left sub list
     * @param right right sub list
     * @param id the id of the product that is search for
     * @return the index of the product.
     */
    private static int binarySearch(List<Product> list, int left, int right, int id) {
        if(right >= left) {
            int mid = left + (right - left) / 2;
            if(list.get(mid).getId() == id) {
                return mid;
            }
            if(list.get(mid).getId() > id) {
                return binarySearch(list, left, mid - 1, id);
            }
            return binarySearch(list, mid + 1, right, id);
        }
        return -1;
    }

    /**
     * Returns the product of a given ID.
     * @param id the ID of the product you want to check.
     * @return the product of the given ID.
     */
    public Product getProductFromAGivenID(int id) {
        mergeSort(this.productList);
        int i = binarySearch(this.productList, 0, this.productList.size() - 1, id);
        if(i == -1) {
            return null;
        }
       return this.productList.get(i);
    }

    /**
     * Returns all the products in the product list
     * @return all products
     */
    public List<Product> getProducts() {
        return this.productList;
    }

    /**
     * Add Product to the product list.
     * @param product the product that is geting added to the list.
     */
    public void addProduct(Product product) {
        this.productList.add(product);
    }

    /**
     * Removes a product form the product list form a given ID.
     * @param id the ID of the product you want to remove from the product list.
     */
    public void deleteProduct(int id) {
        mergeSort(this.productList);
        int i = binarySearch(this.productList, 0, this.productList.size() - 1, id);
        if (i == -1) {
            throw new IllegalArgumentException("Product not found");
        }
        this.productList.remove(i);
    }

    /**
     * Checks if the ID of a product is in the product list.
     * @param id the ID of the product.
     * @return boolean statement, true if found, false if not.
     */
    public boolean checkIfIdIsInTheProductList(int id) {
        mergeSort(this.productList);
        int i = binarySearch(this.productList, 0, this.productList.size() - 1, id);
        if(i == -1) {
            return false;
        }
        return true;
    }
}
