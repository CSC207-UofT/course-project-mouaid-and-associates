package interface_adapters;

import application_business_rules.ManagementSystemFacade;
import frameworks_and_drivers.AddPrescriptionWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppManagerPrescription {
    /**
     * The class that takes care of prescription related tasks in the AppManagerFacade
     */

    private  Map<String, Window> windows;
    private ManagementSystemFacade managementSystemFacade;

    private AppManagerHelpers appManagerHelpers;

    public AppManagerPrescription(Map<String, Window> windows, ManagementSystemFacade managementSystemFacade) {
        this.windows = windows;
        this.managementSystemFacade = managementSystemFacade;
        this.appManagerHelpers = new AppManagerHelpers(managementSystemFacade);
    }

    /**
     * Adds a new prescription to the current user's prescriptions list
     */
    public String addPrescription(){
        Window addPrescriptionWindow = windows.get("Add Prescription Window");
        addPrescriptionWindow.updateFrame();
        String[] inputInfo = new String[2];
        //To see if the input is valid
        boolean validInput = true;
        // Wait until the user has actually responded.
        List<String> medicinesNamesReturn = new ArrayList<>();
        while (!addPrescriptionWindow.userResponded) {
            inputInfo = addPrescriptionWindow.getUserInput();
        }
        //Set it back to false
        addPrescriptionWindow.userResponded =false;
        String[] medicineNames = inputInfo[1].split(",");

        for(String medicine : medicineNames){
            if(!managementSystemFacade.verifyMedicineList(medicine.trim())){
                if (addPrescriptionWindow instanceof AddPrescriptionWindow){
                    ((AddPrescriptionWindow) addPrescriptionWindow).displayErrorMessage(medicine);
                    validInput = false;
                    break;

                }
            }
            else{
                medicinesNamesReturn.add(medicine);
                }
        }
        if (validInput){
            managementSystemFacade.addNewPrescription(medicinesNamesReturn, inputInfo[0]);
        }

        return "View Account Window";

    }

    /**
     * Removes a prescription from the current user's prescriptions list
     */
    public String removePrescription(){
        Window removePrescriptionWindow = windows.get("Remove Prescription Window");
        // Change the view of the screen.
        removePrescriptionWindow.updateFrame();

        String[] inputInfo = new String[1];
        // Wait until the user has actually responded.
        while (!removePrescriptionWindow.userResponded) {
            inputInfo = windows.get("Remove Prescription Window").getUserInput();      // Currently, placeholder.
        }
        managementSystemFacade.removePrescription(inputInfo[0]);
        return "View Account Window";
    }

    /**
     * Edits a prescription which user specifies
     */
    public String editPrescription(){
        Window editPrescriptionWindow = windows.get("Edit Prescription Window");
        Window choosePrescriptionToEditWindow = windows.get("Choose Prescription To Edit Window");
        String[] prescriptions = managementSystemFacade.getPrescriptions();
        String[] presList = this.appManagerHelpers.getFormattedList("List of prescriptions to choose from: ",
                prescriptions, 0, prescriptions.length);

        if (choosePrescriptionToEditWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) choosePrescriptionToEditWindow).displayInfo(presList);
        }
        choosePrescriptionToEditWindow.updateFrame();
        String presName = choosePrescriptionToEditWindow.getUserInput()[0];

        String[] prescription_meds = managementSystemFacade.getPrescription(presName).getPresMedicines();
        if (choosePrescriptionToEditWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) editPrescriptionWindow).displayInfo(prescription_meds);
        }

        editPrescriptionWindow.updateFrame();
        String[] change = new String[3];

        while(!editPrescriptionWindow.userResponded) {
            change = editPrescriptionWindow.getUserInput();
            // For some reason, we don't exit the loop unless I add this line.
            System.out.print("");
        }
        if(!change[0].equals("")){
            managementSystemFacade.changePrescriptionName(presName, change[0]);
        }else if(!change[1].equals("")){
            managementSystemFacade.removeMedicineFromPres(presName, change[1]);
            for(String med : change) {
                String[] medToRemove = new String[1];
                medToRemove[0] = med;
                managementSystemFacade.removeMedicines(medToRemove);
            }
        }else if(!change[2].equals("")){
            Window addMedicineWindow = windows.get("Add Medicine Window");
            String[] data = new String[5];
            addMedicineWindow.updateFrame();
            while(!addMedicineWindow.userResponded) {
                data = editPrescriptionWindow.getUserInput();
                // For some reason, we don't exit the loop unless I add this line.
                System.out.print("");
            }
            this.appManagerHelpers.addMedicineHelper(data);
            managementSystemFacade.addMedicineToPres(presName, data[0]);
        }
        return "View Account Window";
    }
}
