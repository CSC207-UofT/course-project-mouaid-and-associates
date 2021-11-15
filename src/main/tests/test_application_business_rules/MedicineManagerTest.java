package test_application_business_rules;

import application_business_rules.MedicineManager;
import entities.Medicine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MedicineManagerTest {
    Medicine expectedMed;
    MedicineManager medManager;
    List<Map<String, Double>> timeStamps;

    @BeforeEach
    void setUp() {
        medManager = new MedicineManager();

        // Create the medicine to be managed.
        expectedMed = new Medicine("Tylenol", 2, "pills",
                "Take", "");

        // Underneath, we create the times to take the medicine.
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday"};
        double[] times = new double[]{2.0, 23.0, 15.5};
        timeStamps = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            Map<String, Double> timeStamp = new HashMap<>();
            timeStamp.put(days[i], times[i]);
            timeStamps.add(timeStamp);
        }

        expectedMed.addMedicineSchedule(timeStamps);

    }

    @Test
    void createNewMedicine() {
        Medicine actualMed = medManager.createNewMedicine("Tylenol", 2, "pills",
                "Take", "", timeStamps);

        // Now to check every property to see if they match. I will use getMedicineInfo since that is already
        // tested to work. If the medicine info is the same for both meds, then they are the same.
        String[] expectedInfo = expectedMed.getMedicineInfo();
        String[] actualInfo = actualMed.getMedicineInfo();

        for (int i = 0; i < expectedInfo.length; i++){
            assertEquals(expectedInfo[i], actualInfo[i]);
        }
    }
}