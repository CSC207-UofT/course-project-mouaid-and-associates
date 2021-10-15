package interface_adapters;

import java.io.Console;

public class AddMedicineWindow implements Window {

    Console cnsl = System.console();

    @Override
    public String getUserInput() {
        String name = cnsl.readLine("Name of the medicine?");
        String type = cnsl.readLine("Type of the medicine?");
        String wdm = selectWDM(); // Weekly, daily, or monthly
        String amount = cnsl.readLine("Amount of the medicine should be taken");
        String extra = cnsl.readLine("Any Extra Instructions?");

        return "name:" + name + "type:" + type + "wdm" + wdm + "amount" + amount + "extra" + extra;
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

