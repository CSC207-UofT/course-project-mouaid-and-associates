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
            while(!checker.isValidTime(time)){
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
        while (!checker.isNumeric(day) || 0 > Integer.parseInt(day) || Integer.parseInt(day)  > 32){
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
        while (!checker.isNumeric(month) || 0 > Integer.parseInt(month) || Integer.parseInt(month)  > 12 ||
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
            if (checker.isNumeric(input)) {
                return input;
            }
        }
    }

}
