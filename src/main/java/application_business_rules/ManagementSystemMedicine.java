package application_business_rules;

import entities.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class ManagementSystemMedicine {
    /**
     * The class that takes care of all Accounts related tasks in the ManagementSystemFacade
     */

    private ScheduleManager scheduleManager;
    private UserManager userManager;

    /**
     * Creates a new ManagementSystemFacade instance. Also
     * Creates a new UserManager and ScheduleManager.
     */
    public ManagementSystemMedicine(UserManager userManager, ScheduleManager scheduleManager){
        this.userManager = userManager;
        this.scheduleManager = scheduleManager;
    }


    /**
     * Removes the medicines from the list of medicines the user has.
     * @param medsToRemove      The list of meds to be removed.
     */
    public void removeMedicines(String[] medsToRemove){
        userManager.removeMeds(medsToRemove);
    }

    /** Creates a new instance of Medicine. Takes in the parameters necessary
     * to create the new Medicine.
     *
     * @param medicineName             The name of the medicine.
     * @param amount                   The amount of the medicine.
     * @param methodOfAdministration   How the medicine should be administered (e.g. drink, inject, swallow)
     * @param extraInstructions        Any extra instructions with this medication.
     * @param times                    A list of times to take the medication. Each element is a mapping
     *                                 of a day of the week to an hour. See Event's documentation for more
     *                                 details as the format of day and hour. Each element in the list corresponds
     *                                 to one time stamp. Thus taking the same medication multiple times leads to
     *                                 multiple time stamps, hence the list.
     */
    public void addNewMedicine(String medicineName, int amount, String unitOfMeasurement,
                               String methodOfAdministration, String extraInstructions,
                               List<LocalDateTime> times) {
        userManager.createMedicine(medicineName, amount, unitOfMeasurement,
                methodOfAdministration, extraInstructions, times);
    }

    public String[] getMedicineInfo(String medName){
        return userManager.getMedicineInfo(medName);
    }

    /**
     * Edits a medicine using the given info. The first element is the new name of the medicine.
     * The second element is the new unit of measurement, the third element is the new method of administration,
     * the fourth element is the new amount, and the fifth element is the new extra instructions.
     *
     * The list of mappings called times is for the new times to take the medicine.
     *
     * @param info      The info used to edit the medicine. The first element is the medicine name.
     * @param times     The new times to take this medicine.
     */
    public void editMedicine(String medName, String[] info, List<LocalDateTime> times){
        userManager.editMedicine(medName, info);
        scheduleManager.editScheduleTimes(userManager.getMedicineSchedule(medName), times);

        // Change the mapping from the old name to the new name.
        if (!info[0].equals("")){
            userManager.changeMedicineNameInMapping(medName, info[0]);
        }
    }
}
