package frameworks_and_drivers;

import interface_adapters.Window;
import java.util.Scanner;
import java.io.Console;

public class SetSleepTimingsWindow extends Window {
    /**
     * The window that displays the Set Sleeping Time page.
     */

    public SetSleepTimingsWindow(Scanner scanner){
        super(scanner);
    }

    /**
     * Gets input from the user in order to Set the Sleeping Time.
     * @return A list containing the user login input
     */
    @Override
    public String[] getUserInput() {
        String[] returnList = new String[2];
        System.out.println("When do you want to sleep?");
        String sleepTime = scanner.nextLine();
        System.out.println("When do you want to wake up");
        String wakeUpTime = scanner.nextLine();
        returnList[0] = sleepTime;
        returnList[1] = wakeUpTime;
        return returnList;
    }

}
