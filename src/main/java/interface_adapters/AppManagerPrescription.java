package interface_adapters;

import application_business_rules.ManagementSystemFacade;

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
        Window addMedicineWindow = windows.get("Add Medicine Window");
        List<String[]> data = ((PrescriptionWindow) addPrescriptionWindow).getUserPrescriptionInput(addMedicineWindow);
        List<String> medicinesNames = new ArrayList<>();
        for(String[] medicine : data){
            if(medicine.length > 1){
                this.appManagerHelpers.addMedicineHelper(medicine);
                medicinesNames.add(medicine[0]);
            }
        }
        managementSystemFacade.addNewPrescription(medicinesNames, data.get(0)[0]);
        return "View Account Window";

    }

    /**
     * Removes a prescription from the current user's prescriptions list
     */
    public String removePrescription(){
        Window removePrescriptionWindow = windows.get("Remove Prescription Window");
        String[] data = removePrescriptionWindow.getUserInput();
        managementSystemFacade.removePrescription(data[0]);
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
        String presName = choosePrescriptionToEditWindow.getUserInput()[0];
        String[] change = editPrescriptionWindow.getUserInput();
        if(!change[0].equals("")){
            managementSystemFacade.changePrescriptionName(presName, change[0]);
        }else if(!change[1].equals("")){
            managementSystemFacade.removeMedicineFromPres(presName, change[1]);
            String[] medToRemove = new String[1];
            medToRemove[0] = change[1];
            managementSystemFacade.removeMedicines(medToRemove);
        }else if(!change[2].equals("")){
            Window addMedicineWindow = windows.get("Add Medicine Window");
            String[] data = addMedicineWindow.getUserInput();
            this.appManagerHelpers.addMedicineHelper(data);
            managementSystemFacade.addMedicineToPres(presName, data[0]);
        }
        return "View Account Window";
    }
}
