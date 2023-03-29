package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the category class
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
//@SpringJUnitConfig(CategoryTest.Config.class)
//@SpringBootTest
public class CategoryTest {

//    @Test
//    void testConstructorWithCorrectInput() {
//        Category category = new Category(1, "test");
//        assertEquals(1, category.getCid());
//        assertEquals("test", category.getCname());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputCid() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new Category(-1, "test");
//        assertEquals("Caught Illegal Argument Exception: The number cid can not be zero or below.", outContent.toString().trim());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputName() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new Category(1, "");
//        assertEquals("Caught Illegal Argument Exception: The String: cname can not be empty or null.", outContent.toString().trim());
//    }
//    static class Config {
//
//    }
}
