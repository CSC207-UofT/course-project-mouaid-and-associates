package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import java.util.Scanner;

public class ViewAccountWindow extends Window implements DisplayEntityInformation {
    String[] returnList = new String[1];

    public ViewAccountWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
    }

    /**
     * @return "no" if user doesn't want an action, "add", "edit", or "remove" if user wants any of those actions.
     */
    @Override
    public String[] getUserInput() {
        while (true){
            System.out.println("Type 'add' to add a new medicine \n" +
                    "Type 'pres' to add a new prescription \n" +
                    "Type 'remove pres' to remove a prescription \n" +
                    "Type 'edit pres' to edit your prescriptions \n" +
                    "Type 'edit' to edit a medicine\n" +
                    "Type 'remove' to remove a medicine\n" +
                    "Type 'set sleep times' to set your sleep and wakeup times \n" +
                    "Type 'set meal times' to set your meal times \n" +
                    "Type 'view' to view the timetable \n" +
                    "Type 'logout' to logout. \n");
            String input = scanner.nextLine();

            if (input.equals("add") || input.equals("edit") || input.equals("remove") || input.equals("logout")
                    || input.equals("set sleep times") || input.equals("set meal times") || input.equals("view")
                    || input.equals("pres") || input.equals("edit pres") || input.equals("remove pres")){
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

    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override
    public void update(Object source) {

    }


    @Override
    public void createView() {

    }
}
