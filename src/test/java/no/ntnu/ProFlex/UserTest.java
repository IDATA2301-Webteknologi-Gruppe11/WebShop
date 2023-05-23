package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class represent the test class for User.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@SpringBootTest
public class UserTest {

    /**
     * Test constructor with valid parameters
     */
    @Test
    void testConstructor() {
        User user = new User("firstName", "lastName", "email", "pass");
        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertEquals("email", user.getEmail());
        assertEquals("pass", user.getPass());
    }

    /**
     * Test constructor with invalid first name
     */
    @Test
    void testConstructorEmptyFirstName() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    User user = new User("", "lastname", "email", "password");
                }
        );
        assertEquals("The string 'firstName' cant be empty or null",exception.getMessage());
    }

    /**
     * Test constructor with invalid last name
     */
    @Test
    void testConstructorEmptyLastName() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    User user = new User("firstName", "", "email", "password");
                }
        );
        assertEquals("The string 'lastName' cant be empty or null",exception.getMessage());
    }

    /**
     * Test constructor with invalid email
     */
    @Test
    void testConstructorEmptyEmail() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    User user = new User("firstName", "lastName", "", "password");
                }
        );
        assertEquals("The string 'Email' cant be empty or null",exception.getMessage());
    }

    /**
     * Test constructor with invalid pass
     */
    @Test
    void testConstructorEmptyPass() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    User user = new User("firstName", "lastname", "email", "");
                }
        );
        assertEquals("The string 'pass' cant be empty or null",exception.getMessage());
    }

    /**
     * Test if user is valid
     */
    @Test
    void testIsValid() {
        User user = new User("firstName", "lastName", "email", "pass");
        assertTrue(user.isValid());
    }
}

