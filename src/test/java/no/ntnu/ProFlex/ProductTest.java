package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is the test class for product
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
//@SpringJUnitConfig(ProductTest.Config.class)
//@SpringBootTest
public class ProductTest {

//    @Test
//    void testConstructorWithValidInputs() {
//        Product product = new Product("name", 1, 20, "test");
//        assertEquals("name", product.getName());
//        assertEquals(1, product.getId());
//        assertEquals(20, product.getPrice());
//        assertEquals("test", product.getDescription());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputName() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new Product("", 1, 20, "test");
//        assertEquals("Caught Illegal Argument Exception: The String: name can not be empty or null.", outContent.toString().trim());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputId() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new Product("testname", -1, 20, "test");
//        assertEquals("Caught Illegal Argument Exception: The number id can not be zero or below.", outContent.toString().trim());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputPrice() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new Product("testname", 1, -20, "test");
//        assertEquals("Caught Illegal Argument Exception: The number price can not be zero or below.", outContent.toString().trim());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputDescription() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new Product("test", 1, 20, "");
//        assertEquals("Caught Illegal Argument Exception: The String: description can not be empty or null.", outContent.toString().trim());
//    }
//
//    static class Config {
//
//    }
}
