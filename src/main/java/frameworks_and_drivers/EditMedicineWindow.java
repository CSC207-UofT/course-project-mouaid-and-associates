package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ScheduleInputWindow;
import interface_adapters.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class EditMedicineWindow extends ScheduleInputWindow implements DisplayEntityInformation{
    public EditMedicineWindow(Scanner scanner) {
        super(scanner);
    }

    @Override
    public String[] getUserInput() {
        String choice = "0";
        String[] times;
        List<String> changes = new ArrayList<>(List.of(new String[]{"", "", "", ""}));

        while (!choice.equals("6")){
            System.out.println("Enter the number of the information you would like to change:");
            System.out.println("(1) Name of the medicine");
            System.out.println("(2) Method of administration");
            System.out.println("(3) Amount");
            System.out.println("(4) Extra Instructions");
            System.out.println("(5) The times to take the medicine");
            System.out.println("(6) Go back to Account page");

            choice = scanner.nextLine();

            if (choice.equals("1")){
                // get medicine Name
                System.out.println("Enter the new name of the medicine:");
                changes.set(0, scanner.nextLine());
            } else if (choice.equals("2")){
                // get method of administration
                System.out.println("Enter the new method of administration:");
                changes.set(1, scanner.nextLine());
            } else if (choice.equals("3")){
                // get amount
                System.out.println("Enter the new amount of the medicine:");
                changes.set(2, scanner.nextLine());
            } else if (choice.equals("4")){
                // get extra instructions
                System.out.println("Enter the new extra instructions:");
                changes.set(3, scanner.nextLine());
            } else if (choice.equals("5")){
                // If the user has entered times beforehand, clear the previous entry.
                if (changes.size() > 4){
                    changes.subList(4, changes.size()).clear();
                }

                changes.add(super.selectWD(scanner));
                changes.add(super.selectDay(scanner));
                times = super.getTimes(scanner);
                // Add the new entry.
                Collections.addAll(changes, times);
            }

        }
        // Return an array with the same order of items as the parameters of addMedicine.
        return changes.toArray(new String[0]);
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info      The information to be printed.
     */
    @Override
    public void displayInfo(String[] info) {
        for (String line: info){
            System.out.println(line);
        }
    }

}
