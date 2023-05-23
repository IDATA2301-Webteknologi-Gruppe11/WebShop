package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for category entity
 */
@SpringBootTest
public class CategoryTest {

    /**
     * Test the constructor with valid parameters.
     */
    @Test
    void testConstructorWithValidParameters() {
        Category category = new Category("name");
        assertEquals("name", category.getCname());
    }

    /**
     * Test the constructor with invalid name.
     */
    @Test
    void testConstructorEmptyCname() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Category category = new Category(" ");
                }
        );
        assertEquals("The string cname cant be empty or null.",exception.getMessage());
    }

    /**
     * Test the is valid method.
     */
    @Test
    void testIsValid() {
        Category category = new Category("name");
        assertTrue(category.isValid());
    }
}
