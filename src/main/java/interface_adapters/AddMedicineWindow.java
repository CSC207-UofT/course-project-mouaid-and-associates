package interface_adapters;

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

        String[] return_list = new String[5];

        return_list[0] = name;
        return_list[1] = type;
        return_list[2] = wdm;
        return_list[3] = amount;
        return_list[4] = extra;

        return return_list;
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

