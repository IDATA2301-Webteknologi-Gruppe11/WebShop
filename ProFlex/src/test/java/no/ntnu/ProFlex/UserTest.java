package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class represent the test class for User.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
//@SpringJUnitConfig(UserTest.Config.class)
//@SpringBootTest
public class UserTest {

//    @Test
//    void testConstructorWihtValidInputs() {
//        User user = new User(1, "Name", "Email", "Password");
//        assertEquals(1, user.getUid());
//        assertEquals("Name", user.getUname());
//        assertEquals("Email", user.getEmail());
//        assertEquals("Password", user.getPassword());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputUid() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new User(-1, "Name", "Email", "Password");
//        assertEquals("Caught Illegal Argument Exception: The number uid can not be zero or below.", outContent.toString().trim());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputName() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new User(1, "", "Email", "Password");
//        assertEquals("Caught Illegal Argument Exception: The String: uname can not be empty or null.", outContent.toString().trim());
//    }
//
//    @Test
//    void testConstructorWithInvalidInputEmali() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new User(1, "Name", "", "Password");
//        assertEquals("Caught Illegal Argument Exception: The String: email can not be empty or null.", outContent.toString().trim());
//    }
//
//    @Test
//    void testConstructorWhitInvalidPassword() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        new User(1, "Name", "email", "");
//        assertEquals("Caught Illegal Argument Exception: The String: password can not be empty or null.", outContent.toString().trim());
//
//    }
//
//    static class Config {
//
//    }
}
