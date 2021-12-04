package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;
import java.util.Scanner;

public class SetSleepTimingsWindow extends Window {
    /**
     * The window that displays the Set Sleeping Time page.
     */

    public SetSleepTimingsWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
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

    /**
     * Notify the observer of a change
     *
     * @param frame
     * @param source
     */
    @Override
    public void update(ObservableFrame frame, Object source) {

    }

    @Override
    public void createView() {

    }
}
