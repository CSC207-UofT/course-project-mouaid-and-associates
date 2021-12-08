package test_interface_adapters;

import interface_adapters.ValidInputChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidInputCheckerTest {
    ValidInputChecker checker;


    @BeforeEach
    void setUp() {
        checker = new ValidInputChecker();
    }

    @Test
    void isNumericInt() {
        assertTrue(checker.isNumeric("12"));
    }

    @Test
    void isNumericStr() {
        assertFalse(checker.isNumeric("a"));
    }

    @Test
    void isNumericDouble() {
        assertTrue(checker.isNumeric("12.0"));
    }

    @Test
    void isValidTimeTrue() {
        assertTrue(checker.isValidTime("10:30"));
    }

    @Test
    void isValidTimeFalse(){
        assertFalse(checker.isValidTime("10.5"));
    }

    @Test
    void isValidMonthTrue() {
        assertTrue(checker.isValidMonth("8"));
    }

    @Test
    void isValidMonthFalse(){
        assertFalse(checker.isValidMonth("24"));
    }

    @Test
    void isValidDayTrue() {
        assertTrue(checker.isValidDay("31", "12"));
    }

    @Test
    void isValidDayFalse() {
        assertFalse(checker.isValidDay("30", "2"));
    }

    @Test
    void isWeekOrDailyTrue() {
        assertTrue(checker.isWeekOrDaily("daily"));
    }

    @Test
    void isWeekOrDailyFalse(){
        assertFalse(checker.isWeekOrDaily("nope"));
    }
}