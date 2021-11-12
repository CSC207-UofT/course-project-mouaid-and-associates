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
        List<String> changes = new ArrayList<>(List.of(new String[]{"", "", "", "", ""}));

        while (!choice.equals("7")){
            System.out.println("Enter the number of the information you would like to change:");
            System.out.println("(1) Name of the medicine");
            System.out.println("(2) The unit of measurement");
            System.out.println("(3) Method of Administration");
            System.out.println("(4) Dosage amount");
            System.out.println("(5) Extra Instructions");
            System.out.println("(6) The times to take the medicine");
            System.out.println("(7) Go back to Account page");

            choice = scanner.nextLine();

            if (choice.equals("1")){
                // get medicine Name
                System.out.println("Enter the new name of the medicine:");
                changes.set(0, scanner.nextLine());
            } else if (choice.equals("2")){
                System.out.println("Enter the new unit of measurement for the medicine \n " +
                        "(e.g. mL, pill, L, g, mg, tsp, tbsp, pea sized drop, etc.");
                changes.set(1, scanner.nextLine());
            } else if (choice.equals("3")) {
                // get method of administration
                System.out.println("Enter the new method of administration:");
                changes.set(2, scanner.nextLine());
            } else if (choice.equals("4")){
                // get amount
                System.out.println("Enter the new dosage of the medicine. " +
                        "Enter the number only, as it will be measured in the units specified:");
                changes.set(3, scanner.nextLine());
            } else if (choice.equals("5")){
                // get extra instructions
                System.out.println("Enter the new extra instructions:");
                changes.set(4, scanner.nextLine());
            } else if (choice.equals("6")){
                // If the user has entered times beforehand, clear the previous entry.
                if (changes.size() > 5){
                    changes.subList(5, changes.size()).clear();
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
