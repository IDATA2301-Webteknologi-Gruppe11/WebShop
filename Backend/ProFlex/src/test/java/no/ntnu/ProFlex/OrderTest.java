package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * This class represent the test class for Order.
 *
 * @author Ole Kristian Dvergsal
 * @version 1.0
 */
//@SpringJUnitConfig(OrderTest.Config.class)
//@SpringBootTest
public class OrderTest {
//
//    @Test
//    void testConstructorWithValidInputs() {
//        Date date = new Date(2000,11,30);
//        Order order = new Order(1, 2, date);
//        assertEquals(1, order.getOid());
//        assertEquals(2, order.getUid());
//        assertEquals("Sun Dec 30 00:00:00 CET 3900", order.getDate().toString());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputOid() {
//        Date date = new Date(2000,11,30);
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new Order(-1, 2, date);
//        assertEquals("Caught Illegal Argument Exception: The number oid can not be zero or below.", outContent.toString().trim());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputUid() {
//        Date date = new Date(2000,11,30);
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new Order(1, -2, date);
//        assertEquals("Caught Illegal Argument Exception: The number uid can not be zero or below.", outContent.toString().trim());
//    }
//    //TODO Test date(må finne ut korleis man skal sjekke for rikting date i constructor i Order classen.
//
//    static class Config {
//
//    }
}
