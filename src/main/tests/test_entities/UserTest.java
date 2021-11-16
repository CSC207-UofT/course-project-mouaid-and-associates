package test_entities;

import entities.Meal;
import entities.Sleep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.User;
import entities.Medicine;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user;
    Medicine myMedicine;

    @BeforeEach
    void setUp(){
        this.user = new User("Mouaid Alim", "alimmoua", "myPasswordIsSafe");
        myMedicine = new Medicine("Mouaid's medicine", 60, "mL",
                "Injection","needs to be taken 1 hour before eating");
    }

    @Test
    public void TestAddMedicine(){
        user.addMedicine(myMedicine);
        assertTrue(user.getMedicineList().containsValue(myMedicine));
    }

    @Test
    void removeMedicine() {
        user.addMedicine(myMedicine);
        user.removeMedicine(myMedicine.getMedicineName());
        assertFalse(user.getMedicineList().containsValue(myMedicine));
    }

    @Test
    void getMedicine() {
        user.addMedicine(myMedicine);
        Medicine actual_med = user.getMedicine("Mouaid's medicine");
        assertEquals(actual_med, myMedicine);
    }

    @Test
    void changeMedicineNameInMapping() {
        user.addMedicine(myMedicine);
        Medicine expected = user.getMedicine("Mouaid's medicine");
        user.changeMedicineNameInMapping("Mouaid's medicine", "Sujoy's Med");
        Medicine actual = user.getMedicine("Sujoy's Med");
        assertEquals(actual, expected);
    }

    @Test
    void testSleepClass(){
        Sleep sleep = new Sleep();
        this.user.setSleepClass(sleep);
        assertEquals(this.user.getSleepClass(), sleep);
    }

    @Test
    void testGetMealClass(){
        Meal meal = new Meal();
        this.user.setMealClass(meal);
        assertEquals(this.user.getMealClass(), meal);
    }
}
