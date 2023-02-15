package no.ntnu.proflex;

import no.ntnu.proflex.products.ProductList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ProductList class.
 * I test all public method if they work when used correct and when not use correct
 *
 * @author Ole Kristian
 * @version 1.0
 */
class TestProductList {

    //Test Products for testing
    TestProductForTesting testProductForTesting1 = new TestProductForTesting("test1", 1, 1, "test");
    TestProductForTesting testProductForTesting2 = new TestProductForTesting("test2", 2, 2, "test");
    TestProductForTesting testProductForTesting3 = new TestProductForTesting("test3", 3, 3,"test");

    /**
     * Test the getProductFromAGivenId method works when used correct
     */
    @Test
    void testGetProductFromAGivenID() {
        ProductList productList = new ProductList();
        productList.addProduct(testProductForTesting1);
        productList.addProduct(testProductForTesting2);

        assertEquals(productList.getProductFromAGivenID(1).getId(), 1);
        assertEquals(productList.getProductFromAGivenID(1).getName(), "test1");
        assertEquals(productList.getProductFromAGivenID(1).getPrice(), 1);
        assertEquals(productList.getProductFromAGivenID(1).getDescription(), "test");
    }

    /**
     * Test the getProductFromAGivenId method when a given id dose not exists.
     */
    @Test
    void testGetProductFromAGivenIDWhenIdDoseNotExcists() {
        ProductList productList = new ProductList();
        productList.addProduct(testProductForTesting1);

        assertNull(productList.getProductFromAGivenID(2));
    }

    /**
     * Test the getProducts when used correct.
     */
    @Test
    void testGetProducts() {
        ProductList productList = new ProductList();
        productList.addProduct(testProductForTesting1);
        productList.addProduct(testProductForTesting2);
        productList.addProduct(testProductForTesting3);

        assertEquals(productList.getProducts().size(), 3);
        assertEquals(productList.getProducts().get(0).getId(), 1);
        assertEquals(productList.getProducts().get(1).getId(), 2);
        assertEquals(productList.getProducts().get(2).getId(), 3);
    }

    /**
     * Test addProduct method when used correct.
     */
    @Test
    void testAddProduct() {
        ProductList productList = new ProductList();
        assertEquals(productList.getProducts().size(), 0);
        productList.addProduct(new TestProductForTesting("test", 4, 4, "testtest"));
        
        assertEquals(productList.getProducts().size(), 1);
        assertEquals(productList.getProducts().get(0).getName(), "test");
        assertEquals(productList.getProducts().get(0).getId(), 4);
        assertEquals(productList.getProducts().get(0).getPrice(), 4);
        assertEquals(productList.getProducts().get(0).getDescription(), "testtest");
    }

    /**
     * Test deleteProduct method when used correct.
     */
    @Test
    void testDeleteProduct() {
        ProductList productList = new ProductList();
        productList.addProduct(testProductForTesting1);
        productList.addProduct(testProductForTesting2);
        assertEquals(productList.getProducts().size(), 2);
        productList.deleteProduct(1);

        assertEquals(productList.getProducts().size(), 1);
        assertNull(productList.getProductFromAGivenID(1));
    }

    /**
     * Test deleteProduct method when the ID given does not exists.
     */
    @Test
    void testDeletProductWhenWrongIdIsGiven() {
        ProductList productList = new ProductList();
        productList.addProduct(testProductForTesting1);
        productList.addProduct(testProductForTesting2);
        assertEquals(productList.getProducts().size(), 2);
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    productList.deleteProduct(3);
                }
        );
        assertEquals("Product not found", exception.getMessage());
        assertEquals(productList.getProducts().size(), 2);
    }

    /**
     * Test checksIfIdIsInTheProductList method when used correct.
     */
    @Test
    void checkIfIdIsInTheProductList() {
        ProductList productList = new ProductList();
        productList.addProduct(testProductForTesting1);
        productList.addProduct(testProductForTesting2);
        productList.addProduct(testProductForTesting3);

        assertTrue(productList.checkIfIdIsInTheProductList(2));
        assertFalse(productList.checkIfIdIsInTheProductList(4));
    }
}
