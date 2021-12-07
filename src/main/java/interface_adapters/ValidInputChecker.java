package interface_adapters;

import java.util.HashMap;

public class ValidInputChecker {
    /**
     * A class that makes sure that strings meet certain requirements depending on the method
     */
    static HashMap<String, Integer> monthNumDays = new HashMap<>();

    public ValidInputChecker() {
        //Is used to make sure that the data is valid
        monthNumDays.put("1", 31);
        monthNumDays.put("2", 28);
        monthNumDays.put("3", 31);
        monthNumDays.put("4", 30);
        monthNumDays.put("5", 31);
        monthNumDays.put("6", 30);
        monthNumDays.put("7", 31);
        monthNumDays.put("8", 31);
        monthNumDays.put("9", 30);
        monthNumDays.put("10", 31);
        monthNumDays.put("11", 30);
        monthNumDays.put("12", 31);
    }


    /**
     * Checks to see whether str can be converted into a double
     *
     * @param str what it will check to see if it can be converted to a string
     * @return Whether the str can be converted to a double
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks to see if the user inputted time correctly
     *
     * @param time a string given that should represent the time
     * @return Whether time is of the right format
     */
    public boolean isValidTime(String time) {
        if (time.length() != 5 || !isNumeric(String.valueOf(time.charAt(0))) ||
                !isNumeric(String.valueOf(time.charAt(1))) || !isNumeric(String.valueOf(time.charAt(3))) ||
                !isNumeric(String.valueOf(time.charAt(4))) || time.charAt(2) != ':') {
            return false;
        } else if (Character.getNumericValue(time.charAt(0)) * 10 + Character.getNumericValue(time.charAt(1)) >= 24 ||
                Character.getNumericValue(time.charAt(3)) * 10 + Character.getNumericValue(time.charAt(4)) > 59) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks to see if the month is valid
     * @param month The month to check
     * @return whether the month is greater or less than 12
     */
    public boolean isValidMonth(String month) {
        if (Integer.parseInt(month) > 12) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks to see if the day is valid given the month
     * @param day The day to check, which is a string that can be converted to an integer
     * @param month The month to check, a string that can be converted to an integer
     * @return Whether the day is in the month
     */
    public boolean isValidDay(String day, String month) {
        if (monthNumDays.get(String.valueOf(Integer.parseInt(month))) < Integer.parseInt(day)) {
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks to see if the user input weekly or daily
     * @param occurrence The string to check
     * @return Whether the string is equal to weekly or daily
     */
    public boolean isWeekOrDaily(String occurrence){
        if (!occurrence.equals("daily") &&
                !occurrence.equals("weekly")){
            return false;
        }
        else{
            return true;
        }
    }
}
