package test_application_business_rules;

import application_business_rules.MedicineManager;
import application_business_rules.UserManager;
import entities.Medicine;
import entities.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserManagerTest {
    @Test
    public void TestAddNewUser() {
        MedicineManager medicineManager = new MedicineManager();
        UserManager usermanager = new UserManager();
        Medicine medicine = new Medicine("Panadol", 40, "Eat",
                "No extra instructions");
        User user =  usermanager.addNewUser("Mohamed", "moe123");
        assertEquals(usermanager.getUser(), user);
    }
}