package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for entity role
 */
@SpringBootTest
public class RoleTest {

    /**
     * Test constructor with valid parameters
     */
    @Test
    void testConstructorWithValidParameters() {
        Role role = new Role("rname");
        assertEquals("rname", role.getName());
    }

    /**
     * Test constructor with invalid name.
     */
    @Test
    void testConstructorInvalidName() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Role role = new Role("");
                }
        );
        assertEquals("The string 'name' cannot be empty or null.", exception.getMessage());
    }

    /**
     * Test is if a role is valid
     */
    @Test
    void testIsValid() {
        Role role = new Role("validName");
        assertTrue(role.isValid());
    }
}
