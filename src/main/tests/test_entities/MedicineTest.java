//package test_entities;
//
//import entities.Medicine;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MedicineTest {
//
//    public Medicine med;
//
//    @BeforeEach
//    void setUp() {
//        List<Map<String, Double>> times = new ArrayList<>();
//        med = new Medicine("Tylenol", 2, "pills",
//                "Take", "");
//        med.addMedicineSchedule(times);
//    }
//
//    @Test
//    void makeDescriptionNoAmount() {
//        // Change the amount to 0
//        med.setAmount(0);
//        String expected = "Take Tylenol. ";
//        String actual = med.makeDescription();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void makeDescriptionWithAmount() {
//        String expected = "Take 2 Tylenol pills. ";
//        String actual = med.makeDescription();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void getMedicineInfoWithNoTimes() {
//        String[] expected = new String[]{"Name of Medicine: Tylenol",
//                "How the dosage is measured: pills",
//                "Dosage amount: 2",
//                "Method of Administration: Take",
//                "Extra Instructions: ",
//                "Times: "};
//
//        String[] actual = med.getMedicineInfo();
//
//        assertEquals(expected.length, actual.length);
//
//        for (int i = 0; i < expected.length; i++){
//            assertEquals(expected[i], actual[i]);
//        }
//    }
//
//    @Test
//    void getMedicineInfoWithTimes() {
//        // Make a new timestamp for Monday at 10:00 AM
//        Map<String, Double> time1 = new HashMap<>();
//        time1.put("Monday", 10.0);
//
//        // Make a new list of maps for the time stamps, and add in time1
//        List<Map<String, Double>> times = new ArrayList<>();
//        times.add(time1);
//
//        // Add a new schedule with times to med.
//        med.addMedicineSchedule(times);
//
//        String[] expected = new String[]{"Name of Medicine: Tylenol",
//                "How the dosage is measured: pills",
//                "Dosage amount: 2",
//                "Method of Administration: Take",
//                "Extra Instructions: ",
//                "Times: ",
//                "   - Monday: 10:00"};
//
//        String[] actual = med.getMedicineInfo();
//
//        assertEquals(expected.length, actual.length);
//
//        for (int i = 0; i < expected.length; i++){
//            assertEquals(expected[i], actual[i]);
//        }
//
//    }
//
//    @Test
//    void getMedicineInfoWithNegativeAmount(){
//        med.setAmount(-1);
//        String[] expected = new String[]{"Name of Medicine: Tylenol",
//                "How the dosage is measured: pills",
//                "Dosage amount: ",
//                "Method of Administration: Take",
//                "Extra Instructions: ",
//                "Times: "};
//
//        String[] actual = med.getMedicineInfo();
//
//        assertEquals(expected.length, actual.length);
//
//        for (int i = 0; i < expected.length; i++){
//            assertEquals(expected[i], actual[i]);
//        }
//
//    }
//}