package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the category class
 */
public class TestCategory {

    @Test
    void testConstructorWithValidInputs() {
        Category category = new Category(1, "test");

        assertEquals(category.getCid(), 1);
        assertEquals(category.getCname(), "test");
    }

    @Test
    void testGetName() {
        Category category = new Category(1, "test");
        assertEquals(category.getCname(), "test");
    }

    @Test
    void testGetId() {
        Category category = new Category(1, "test");
        assertEquals(category.getCid(), 1);
    }
}
