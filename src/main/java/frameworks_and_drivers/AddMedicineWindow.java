package frameworks_and_drivers;

import interface_adapters.Window;

import java.util.Scanner;

public class AddMedicineWindow implements Window {

    Console cnsl = System.console();
    @Override
    public String[] getUserInput() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What medicine do you want to take?");
        String name = scanner.nextLine();

        System.out.println("How are you taking the medicine");
        String methodOfAdministration = scanner.nextLine();

        System.out.println("How much medicine do you need to take each time");
        String amount = scanner.nextLine();

        System.out.println("Are there any additional instructions that should be noted?");
        String extra = scanner.nextLine();

        String wdm = selectWD(scanner); // Weekly, daily
        String startDate = selectDay(scanner);
        String[] times = getTimes(scanner);
        String[] returnList = new String[6 + times.length];

        returnList[0] = name;
        returnList[1] = methodOfAdministration;
        returnList[2] = amount;
        returnList[3] = extra;
        returnList[4] = wdm;
        returnList[5] = startDate;
        for (int i = 6; i < (times.length + 6); i++){
            returnList[i] = times[i - 6];
        }

        return returnList;
    }

    private String[] getTimes() {
        String howManyTimesStr = selectHowManyTimes();

        int howManyTimesInt = Integer.parseInt(howManyTimesStr);
        String[] times = new String[howManyTimesInt];
        String time = " ";
        for (int i = 0; i < howManyTimesInt; i++){
            while(!isNumeric(time) || 0 > Double.parseDouble(time) || 24 <= Double.parseDouble(time)){
                System.out.println("For the "+ (i + 1) + "'st time, pick a time (In the form of XX.XX." +
                        "Note that  such as 10:30 becomes " +
                        "10.5, or 13:45 as 13.75");
                time = scanner.nextLine();
            }
            times[i] = time;
            time = " ";
        }
        return times;
    }

    public String selectWD() {

        while (true) {
            System.out.println("Do you need to take it weekly or daily?");
            String input = scanner.nextLine();
            if (input.equals("weekly") || input.equals("daily")) {
                return input;
            }
        }
    }
    public String selectHowManyTimes() {

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
    private  boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}

