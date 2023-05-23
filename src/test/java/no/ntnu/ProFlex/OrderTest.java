package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

/**
 * Test Order entity
 */
@SpringBootTest
public class OrderTest {

    /**
     * Test constructor with valid parameters.
     */
    @Test
    void testConstructor() {
        Date date = new Date(2023,03,13);
        User user = new User("firstName", "lastName", "email", "password");
        Order order = new Order(date, user);
        assertEquals("Fri Apr 13 00:00:00 CEST 3923", order.getDate().toString());
        assertEquals("firstName", order.getUid().getFirstName());
    }

    /**
     * Test if order is valid.
     */
    @Test
    void testIsValid() {
        Date date = new Date(2023,03,13);
        User user = new User("firstName", "lastName", "email", "password");
        Order order = new Order(date, user);
        assertTrue(order.isValid());
    }
}
