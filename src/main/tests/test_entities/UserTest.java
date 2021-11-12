package test_entities;

import org.junit.jupiter.api.Test;
import entities.User;
import entities.Medicine;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void TestAddMedicine(){
        User myUser = new User("Mouaid Alim", "alimmoua");
        Medicine myMedicine = new Medicine("Mouaid's medicine", 60, "mL",
                "Injection","needs to be taken 1 hour before eating");
        myUser.addMedicine(myMedicine);
        assertTrue(myUser.getMedicineList().containsValue(myMedicine));
    }
}
