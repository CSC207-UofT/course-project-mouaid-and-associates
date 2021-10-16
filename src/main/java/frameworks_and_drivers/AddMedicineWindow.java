package frameworks_and_drivers;

import interface_adapters.Window;

import java.io.Console;
import java.util.ArrayList;

public class AddMedicineWindow implements Window {

    Console cnsl = System.console();
    @Override
    public String[] getUserInput() {
        String name = cnsl.readLine("Name of the medicine?");
        String methodOfAdministration = cnsl.readLine("Method of administration");
        String amount = cnsl.readLine("Amount of the medicine should be taken");
        String extra = cnsl.readLine("Any Extra Instructions?");

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
            while(time.length() != 5 || time.charAt(2) !=':' || Character.isDigit(time.charAt(0)) ||
                    Character.isDigit(time.charAt(1)) || Character.isDigit(time.charAt(3))||
                    Character.isDigit(time.charAt(4))){
                time = cnsl.readLine("For the"+ i + "'st What time (In the form of XX:XX and 24 hour time)");
            }
            times[i] = time;
        }
        return times;
    }

    public String selectWD() {

        while (true) {
            String input = cnsl.readLine("Weekly or daily?");
            if (input.equals("weekly") || input.equals("daily")) {
                return input;
            }
        }
    }
    public String selectHowManyTimes() {

        while (true) {
            String input = cnsl.readLine("How many times?");
            if (isNumeric(input)) {
                return input;
            }
        }
    }

    private  boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}

