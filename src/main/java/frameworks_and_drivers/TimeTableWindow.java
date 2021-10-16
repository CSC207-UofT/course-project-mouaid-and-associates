package frameworks_and_drivers;


import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;
import java.util.Scanner;

public class TimeTableWindow extends Window implements DisplayEntityInformation {
    /**
     * The window that displays the Timetable page.
     */
    public TimeTableWindow(Scanner scanner) {
        super(scanner);
    }

    /**
     * Gets input from the user on whether they want to go to the View Account Page
     * @return A list containing the user input
     */
    @Override
    public String[] getUserInput() {

        // returns 1 if the user goes back to ViewAccount..
        String[] returnList = new String[1];
        while (true) {
            System.out.println("If you want to go back to ViewAccount type VA");
            String input = scanner.nextLine();
            if (input.equals("VA")) {
                returnList[0] = "1";
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
