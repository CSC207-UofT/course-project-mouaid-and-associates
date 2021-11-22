package interface_adapters;

import application_business_rules.ManagementSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppManagerMedicine {
    /**
     * The class that takes care of Medicine related tasks in the AppManagerFacade
     */

    Map<String, Window> windows;
    ManagementSystem managementSystem;
    AppManagerHelpers appManagerHelpers;

    public AppManagerMedicine(Map<String, Window> windows, ManagementSystem managementSystem) {
        this.windows = windows;
        this.managementSystem = managementSystem;
        this.appManagerHelpers = new AppManagerHelpers(managementSystem);
    }

    /**
     * Allows the user to pick a specific medicine and edit that medication's information and the times
     * to take that medication.
     */
    public String editMedicine(){
        // First instantiate a window for EditMedicineWindow
        Window editMedicineWindow = windows.get("Edit Medicine Window");
        // Next, instantiate a window for ChooseMedicineToEditWindow
        Window chooseMedicineToEditWindow = windows.get("Choose Medicine To Edit Window");

        // Get a list of medicine names, like for removeMedicine
        // First get a list of user information
        String[] bigList = managementSystem.getUserInfo().toArray(new String[0]);

        // Isolate and get the list of medicine names
        String[] medList = this.appManagerHelpers.getFormattedList("List of medicines to choose from: ", bigList,
                2, bigList.length);
        // First, instantiate a variable to store the name of the medicine the user would like to remove.
        String medName;
        String[] medInfo;
        String[] changes; // Represents the changes the user wants to make.
        List<LocalDateTime> newTimes = new ArrayList<>();

        // Check if ChooseMedicineToEditWindow is an instance of DisplayEntityInformation to print
        // out the list of medicine names, like in removeMedicine()
        if (chooseMedicineToEditWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) chooseMedicineToEditWindow).displayInfo(medList);
        }


        medName = chooseMedicineToEditWindow.getUserInput()[0];

        medInfo = managementSystem.getMedicineInfo(medName);

        if (editMedicineWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) editMedicineWindow).displayInfo(medInfo);
        }

        changes = editMedicineWindow.getUserInput();

        // Format the given times.
        if (changes.length > 5) {
            appManagerHelpers.formatTimes(changes, changes[5], changes[6], changes[7], newTimes);
        }

        // Call management system to edit the entities.
        managementSystem.editMedicine(medName, changes, newTimes);

        return "View Account Window";
    }



    /** This method is to run the functionality of removing a medicine, from showing the menu to
     * calling the appropriate classes to remove the medicine from the account.
     */
    public String removeMedicine(){
        Window removeMedWindow = windows.get("Remove Medicine Window");
        String[] userInfo = managementSystem.getUserInfo().toArray(new String[0]);

        // Get a formatted list to make the printing of the medicine names nice.
        String[] infoToPrint = appManagerHelpers.getFormattedList("The list of medicines to pick from: ",
                userInfo, 2, userInfo.length);

        // Display the list of medicine names.
        if (removeMedWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) removeMedWindow).displayInfo(infoToPrint);
        }

        // Get the list of medicine names the user wants to remove
        String[] inputs = removeMedWindow.getUserInput();

        // Call management system to remove the medicines
        managementSystem.removeMedicines(inputs);

        // go back to the account page.
        return "View Account Window";
    }

    /**
     * Shows the add medicine page. Allows user interaction with the program so that
     * the user can add a new medicine.
     */
    public String addMedicine(){
        //Done: call AddMedicineWindow to display the fields to enter data about the medicine
        Window addMedicineWindow = windows.get("Add Medicine Window");

        String[] data = addMedicineWindow.getUserInput();

        appManagerHelpers.addMedicineHelper(data);

        //Done: call showAccountWindow
        return "View Account Window";
    }


}