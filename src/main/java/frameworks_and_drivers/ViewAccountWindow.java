package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;

import java.io.Console;
import java.util.List;
import java.util.Objects;

public class ViewAccountWindow implements Window, DisplayEntityInformation {

    Console cnsl = System.console();

    /**
     * @return "no" if user doesn't want an action, "add", "edit", or "remove" if user wants any of those actions.
     */
    String[] returnList = new String[1];
    @Override
    public String[] getUserInput() {
        if (selectAction().equals("yes")){
            returnList[0] = selectMedicineAction();
        }
        else{
            returnList[0] = "no";
        }
        return returnList;

    }

    /**
     * @return "yes" or "no"
     **/
    public String selectAction(){

        while (true){
            String input = cnsl.readLine("Do you want to add a new medicine, remove a medicine, edit a medicine, " +
                    "look the medication timetable or logout? Type yes or no");
            if (input.equals("yes") || input.equals("no")) {
                return input;
            }
        }

    }
    /**
     * @return "add" or "edit" or "remove" or "logout" or "view timetable"
     **/
    public String selectMedicineAction(){

        while (true){
            String input = cnsl.readLine("Add, edit or remove?");
            if (input.equals("add") || input.equals("edit") || input.equals("remove")) {
                return input;
            }
        }

        //TODO: Add the options for logging out and viewing time table.

    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info  Information to be displayed onto the screen.
     */
    @Override
    public void displayInfo(String[] info) {
        for (String pieceOfInfo : info){
            System.out.println(pieceOfInfo);
        }
    }
}
