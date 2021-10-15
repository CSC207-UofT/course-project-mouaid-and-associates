package frameworks_and_drivers;

import interface_adapters.Window;

import java.io.Console;
import java.util.ArrayList;

public class AddMedicineWindow implements Window {

    Console cnsl = System.console();

    @Override
    public String[] getUserInput() {
        String name = cnsl.readLine("Name of the medicine?");
        String type = cnsl.readLine("Type of the medicine?");
        String wdm = selectWDM(); // Weekly, daily, or monthly
        String amount = cnsl.readLine("Amount of the medicine should be taken");
        String extra = cnsl.readLine("Any Extra Instructions?");

        String wdm = selectWD(); // Weekly, daily
        String[] times = getTimes();
        String[] returnList = new String[4 + times.length];

        returnList[0] = name;
        returnList[1] = methodOfAdministration;
        returnList[2] = amount;
        returnList[3] = extra;
        for (int i = 4; i < (times.length + 4); i++){
            returnList[i] = times[i - 4];
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

    public String selectWDM() {

        while (true) {
            String input = cnsl.readLine("Weekly, daily or monthly?");
            if (input.equals("weekly") || input.equals("daily") || input.equals("monthly")) {
                return input;
            }
        }
    }
}

