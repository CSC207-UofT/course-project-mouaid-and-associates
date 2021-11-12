package frameworks_and_drivers;

import interface_adapters.ScheduleInputWindow;

import java.util.Scanner;

public class AddMedicineWindow extends ScheduleInputWindow {
    /*
     * The window that displays the Add a Medicine page.
     */

    public AddMedicineWindow(Scanner scanner) {
        super(scanner);
    }

    /**
     * Gets input from the user in order to add a medicine that the user uses in the app.
     *
     * The elements are the following order: medicine name, method of administration, the unit of
     * measurement of the medicine (i.e. it's type), the amount of medicine using the unit of measurement,
     * any extra instructions, the option of taking the medicine weekly or daily, and the starting date of
     * the medicine. All elements afterwords are the different times to take the medicine.
     * @return      A list containing the user input
     */
    @Override
    public String[] getUserInput() {
        // Ask the user for all the different values
        System.out.println("What medicine do you want to take?");
        String name = scanner.nextLine();

        System.out.println("How are you taking the medicine");
        String methodOfAdministration = scanner.nextLine();

        System.out.println("What is the unit of measurement (e.g. pill, mL, tsp, mg, etc.)");
        String unitOfMeasurement = scanner.nextLine();

        System.out.println("How much medicine do you need to take each time, using the unit entered before");
        String amount = scanner.nextLine();

        System.out.println("Are there any additional instructions that should be noted?");
        String extra = scanner.nextLine();

        String wdm = super.selectWD(scanner); // Weekly, daily
        String startDate = super.selectDay(scanner);
        String[] times = super.getTimes(scanner);
        String[] returnList = new String[7 + times.length];

        // Format it to a specific order according to the documentation.
        returnList[0] = name;
        returnList[1] = methodOfAdministration;
        returnList[2] = unitOfMeasurement;
        returnList[3] = amount;
        returnList[4] = extra;
        returnList[5] = wdm;
        returnList[6] = startDate;
        for (int i = 7; i < (times.length + 7); i++){
            returnList[i] = times[i - 7];
        }

        return returnList;
    }
}


