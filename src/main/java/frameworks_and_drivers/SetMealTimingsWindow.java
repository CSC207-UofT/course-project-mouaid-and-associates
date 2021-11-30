package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SetMealTimingsWindow extends Window {
    /**
     * The window that displays the Set Meal Time page.
     */

    public SetMealTimingsWindow(Scanner scanner, ObservableFrame frame){ super(scanner, frame);}

    /**
     * Gets input from the user in order to Set the Meal Times.
     * @return A list containing the user login input
     */
    @Override
    public String[] getUserInput() {
        List<String> returnList = new ArrayList<>();
        System.out.println("How many meals do you have in a day?");
        String numberOfMeals = scanner.nextLine();
        int numbOfMeals = Integer.parseInt(numberOfMeals);
        for (int i = 0; i < numbOfMeals; i++){
            int u = i + 1;
            System.out.println("Please enter the time for meal number: " + u);
            String mealTime = scanner.nextLine();
            returnList.add(mealTime);
        }

        return returnList.toArray(new String[0]);
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
