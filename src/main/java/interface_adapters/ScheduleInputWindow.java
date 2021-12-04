package interface_adapters;

import java.util.Scanner;

public abstract class ScheduleInputWindow extends Window{

    public ScheduleInputWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
    }

    /**
     * Gets input from the user on the times they will be taking the medicine they are trying to add.
     * @return The times the user will be taking the medicine they are trying to add.
     */
    protected String[] getTimes(Scanner scanner) {
        String howManyTimesStr = selectHowManyTimes(scanner);

        int howManyTimesInt = Integer.parseInt(howManyTimesStr);
        String[] times = new String[howManyTimesInt];
        String time = " ";
        for (int i = 0; i < howManyTimesInt; i++){
            while(!isValidTime(time)){
                System.out.println("For the "+ (i + 1) + "'st time, pick a time (In the form of XX:XX. Use the 24 hour " +
                        "format, so that 10:30 is 10:30 am and 22:30 is 10:30 pm");
                time = scanner.nextLine();
            }
            times[i] = time;
            time = " ";
        }
        return times;
    }
    /**
     * Gets input from the user whether the medicine they want to add will be taken weekly or daily
     * @return Whether the user inputted weekly or daily.
     */
    public String selectWD(Scanner scanner) {

        while (true) {
            System.out.println("Do you need to take it weekly or daily?");
            String input = scanner.nextLine();
            if (input.equals("weekly") || input.equals("daily")) {
                return input;
            }
        }
    }

    /**
     * Allows the user to select what day they want to start recording when they should take the medicine
     * @return A string representing the day to start on
     */
    public String selectDay(Scanner scanner){
        String day = "";
        while (!isNumeric(day) || 0 > Integer.parseInt(day) || Integer.parseInt(day)  > 32){
            System.out.println("What day of the month would you like to start?");
            day = scanner.nextLine();
        }

        return day;
    }

    /**
     * Allows the user to select the month they want to start on
     * @return An integer representing the starting month
     */
    public String selectMonth (Scanner scanner) {
        String month = "  ";
        while (!isNumeric(month) || 0 > Integer.parseInt(month) || Integer.parseInt(month)  > 12 ||
                month.length() != 2){
            System.out.println("What month would you like to start? Please month the month in the form 01 for January" +
                    "02 for February and so on");
            month = scanner.nextLine();
        }
        return month;
    }

    /**
     * Gets input and allows the user to decide how many times they should take medicine in a day
     * @return an integer representing how many times in a day you will take the medicine
     */
    public String selectHowManyTimes(Scanner scanner) {

        while (true) {
            System.out.println("How many times do you need to take it in a day?");
            String input = scanner.nextLine();
            if (isNumeric(input)) {
                return input;
            }
        }
    }

    /**
     * Checks to see whether str can be converted into a double
     * @param str what it will check to see if it can be converted to a string
     * @return Whether the str can be converted to a double
     */
    protected boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Checks to see if the user inputted time correctly
     * @param time a string given that should represent the time
     * @return Whether time is of the right format
     */
    public boolean isValidTime(String time){
        if (time.length() != 5 || !isNumeric(String.valueOf(time.charAt(0))) ||
                !isNumeric(String.valueOf(time.charAt(1))) || !isNumeric(String.valueOf(time.charAt(3))) ||
                !isNumeric(String.valueOf(time.charAt(4))) || time.charAt(2) != ':'){
            return false;
        }
        else if (Character.getNumericValue(time.charAt(0)) * 10 + Character.getNumericValue(time.charAt(1)) >= 24 ||
                Character.getNumericValue(time.charAt(3)) * 10 + Character.getNumericValue(time.charAt(4)) > 59){
            return false;
        }
        else{
            return true;
        }


    }
}
