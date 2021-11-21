package frameworks_and_drivers;

import interface_adapters.ScheduleInputWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditPrescriptionWindow extends ScheduleInputWindow {

    public EditPrescriptionWindow(Scanner scanner){
        super(scanner);
    }
    @Override
    public String[] getUserInput() {
        List<String> change = new ArrayList<>(List.of(new String[]{"", "", ""}));
        String choice = "0";
        while(!choice.equals("4")){
            System.out.println("What would you like to edit in the prescription? Type the number that corresponds " +
                    "to it");
            System.out.println("(1) name of the prescription");
            System.out.println("(2) Remove a medicine from the prescription");
            System.out.println("(3) Add a medicine to this prescription");
            System.out.println("(4) Go back to the account page");
            choice = scanner.nextLine();

            if(choice.equals("1")){
                System.out.println("Enter the new name of prescription");
                change.set(0, scanner.nextLine());
            } else if(choice.equals("2")){
                System.out.println("Type the name of the medicine that you would like to remove");
                change.set(1, scanner.nextLine());
            } else if(choice.equals("3")){
                System.out.println("You'll be prompted to the add new medicine form after you click option 4");
                change.set(2, "3");
            }

        }

        return change.toArray(new String[0]);
    }
}
