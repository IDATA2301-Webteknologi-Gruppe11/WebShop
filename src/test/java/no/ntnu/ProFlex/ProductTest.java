package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for product entity
 */
@SpringBootTest
public class ProductTest {

    @Test
    void testConstructorWithValidParameters() {
        Product product = new Product("product", 10, "description", true, "path", "shortD");
        assertEquals("product", product.getName());
        assertEquals(10, product.getPrice());
        assertEquals("description", product.getDescription());
        assertTrue(product.getNewProduct());
        assertEquals("path", product.getImage());
        assertEquals("shortD", product.getShortDescription());
    }

    @Test
    void testConstructorInvalidName() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Product product = new Product("", 10, "description", true, "path", "shortD");
                }
        );
        assertEquals("The string name cant be empty or null.",exception.getMessage());
    }

    @Test
    void testConstructorInvalidPrice() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Product product = new Product("name", -10, "description", true, "path", "shortD");
                }
        );
        assertEquals("The integer price cant be below 0",exception.getMessage());
    }

    @Test
    void testConstructorInvalidDescription() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Product product = new Product("name", 10, "", true, "path", "shortD");
                }
        );
        assertEquals("The string description cant be empty or null.",exception.getMessage());
    }

    @Test
    void testConstructorInvalidImage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Product product = new Product("name", 10, "description", true, "", "shortD");
                }
        );
        assertEquals("The string image cant be empty or null.",exception.getMessage());
    }

    @Test
    void testConstructorInvalidShortDescription() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Product product = new Product("name", 10, "description", true, "path", "");
                }
        );
        assertEquals("The string shortDescription cant be empty or null.",exception.getMessage());
    }

    @Test
    void testIsValid() {
        Product product = new Product("product", 10, "description", true, "path", "shortD");
        assertTrue(product.isValid());
    }
}
