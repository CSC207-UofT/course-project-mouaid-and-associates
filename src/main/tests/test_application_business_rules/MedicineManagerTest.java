package test_application_business_rules;

import application_business_rules.MedicineManager;
import entities.Medicine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MedicineManagerTest {
    Medicine expectedMed;
    MedicineManager medManager;
    List<LocalDateTime> timeStamps;

    @BeforeEach
    void setUp() {
        medManager = new MedicineManager();

        // Create the medicine to be managed.
        expectedMed = new Medicine("Tylenol", 2, "pills",
                "Take", "");

        timeStamps = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            LocalDateTime timeStamp = LocalDateTime.parse("2021-12-03T10:25");
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