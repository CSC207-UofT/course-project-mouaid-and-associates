package test_application_business_rules;

import application_business_rules.ManagementSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagementSystemTest {
    ManagementSystem testSystem;
    @BeforeEach
    public void setUp() {
        testSystem = new ManagementSystem();
    }

    @Test
    public void testCreateNewUser() {
        testSystem.createNewUser("Benjamin", "Ben", "password");
        assertEquals(1,testSystem.getAccounts().size());
        assertTrue(testSystem.getAccounts().containsKey("Ben"));
        List<String>info = testSystem.getUserInfo();
        assertEquals(info.indexOf("Ben"), 1);
        assertEquals(info.indexOf("Benjamin"), 0);


    }
}