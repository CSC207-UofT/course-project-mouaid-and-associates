package frameworks_and_drivers;


import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;
import java.util.Scanner;
import java.io.Console;

public class TimeTableWindow implements Window, DisplayEntityInformation {

    Console cnsl = System.console();

    @Override
    public String[] getUserInput() {

        // returns 1 if the user goes back to ViewAccount..
        String[] returnList = new String[1];
        while (true) {
            Scanner scanner = new Scanner(System.in);
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
