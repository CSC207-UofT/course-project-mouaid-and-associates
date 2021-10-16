package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;

import java.io.Console;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ViewAccountWindow implements Window, DisplayEntityInformation {

    Console cnsl = System.console();
    String[] returnList = new String[1];

    /**
     * @return "no" if user doesn't want an action, "add", "edit", or "remove" if user wants any of those actions.
     */
    @Override
    public String[] getUserInput() {
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type 'add' to add a new medicine \n" +
                    "Type 'edit' to edit a medicine\n" +
                    "Type 'remove' to remove a medicine\n" +
                    "Type 'view' to view the timetable \n" +
                    "Type 'logout' to logout. \n");
            String input = scanner.nextLine();

            if (input.equals("add") || input.equals("edit") || input.equals("remove") || input.equals("logout")
                    || input.equals("view")) {
                returnList[0] = input;
                return returnList;
            }
        }
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
