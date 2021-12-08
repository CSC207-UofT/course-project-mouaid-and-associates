package interface_adapters;

import application_business_rules.FileReaderAndWriter;
import application_business_rules.ManagementSystemFacade;

import java.util.*;
public class AppManagerFacade {
    /** This is the main class that runs the entire app.
     *
     * Instance Attributes:
     * - managementSystemFacade: An input and output boundary between the interface adapters like AppManagerFacade
     *                     and use case interactors like UserManager and MedicineManager.
     * - windows: A mapping of a string name for a window to a Window object.
     * - accounts: A mapping of usernames to passwords. Will likely be changed to be stored in a database
     *             in the future.
     *
     * Representation Invariants:
     * - The keys of windows are {"Login Window", "Create Account Window", "Start Screen Window",
     *                            "TimeTable Window", "View Account Window", "Add Medicine Window",
     *                            "Edit Medicine Window", "Remove Medicine Window",
     *                            "Choose Medicine To Edit Window", "Set Sleep Timings Window",
     *                            "Remove Prescription Window", "Add Prescription Window" }
     *
     *                            * More may be added in the future.
     *
     */

    private ManagementSystemFacade managementSystemFacade;
    private Map<String, Window> windows;
    private AppManagerActivitySetter appManagerActivitySetter;
    private AppManagerMedicine appManagerMedicine;
    private AppManagerPrescription appManagerPrescription;
    private AppManagerAccounts appManagerAccounts;
    private AppManagerPresenters appManagerPresenters;

    public AppManagerFacade(){
        managementSystemFacade = new ManagementSystemFacade();
    }

    /** The main runner of the program. It starts of the app.
     *
     * @param windows   All the windows to be used by the app.
     */
    public void run(Map<String, Window> windows, FileReaderAndWriter readerAndWriter, String accountFile){
        // Creates an array for Window objects. Once the program starts, we will only have a set
        // number of windows, thus an array makes sense.
        this.windows = windows;
        this.appManagerActivitySetter = new AppManagerActivitySetter(this.windows, this.managementSystemFacade);
        this.appManagerMedicine = new AppManagerMedicine(this.windows, this.managementSystemFacade);
        this.appManagerPrescription = new AppManagerPrescription(this.windows, this.managementSystemFacade);
        this.appManagerAccounts = new AppManagerAccounts(this.windows, this.managementSystemFacade);
        this.appManagerPresenters = new AppManagerPresenters(this.windows, this.managementSystemFacade);

        // Set up the user accounts from the file
        setUpAccounts(accountFile, readerAndWriter);

        // Create a variable that sends us to the next window.
        String next_window;
        //Done: Add a call to Start Screen Window
        next_window = showStartScreenWindow();

        // The main switch-case block which calls the different functionalities and windows of the app.
        while(!next_window.equals("Log Out")){
            switch (next_window){
                case "Login Window":
                    next_window = login();
                    break;
                case "Create Account Window":
                    next_window = createNewAccount();
                    break;
                case "TimeTable Window":
                    next_window = showFinalSchedule();
                    break;
                case "View Account Window":
                    next_window = showAccountWindow();
                    break;
                case "Add Medicine Window":
                    next_window = addMedicine();
                    break;
                case "Edit Medicine Window":
                    next_window = editMedicine();
                    break;
                case "Edit Prescription Window":
                    next_window = editPrescription();
                    break;
                case "Remove Medicine Window":
                    next_window = removeMedicine();
                    break;
                case "Set Sleep Timings Window":
                    next_window = setSleepTimes();
                    break;
                case "Set Meal Timings Window":
                    next_window = setMealTimes();
                    break;
                case "Add Prescription Window":
                    next_window = addPrescription();
                    break;
                case "Remove Prescription Window":
                    next_window = removePrescription();
                    break;
                default:
                    next_window = "Log Out";
                    break;
            }
        }

        // Save user information.
        saveAccounts(accountFile, readerAndWriter);
    }

    /**
     * Sets up the user accounts based on the file passed in.
     *
     * Precondition:
     * - The file corresponding to filename is a file storing a mapping of username to User object.
     *
     * @param filename          The name of the file storing accounts
     * @param readerAndWriter   The data access interface
     */
    public void setUpAccounts(String filename, FileReaderAndWriter readerAndWriter){
        this.appManagerAccounts.setUpAccounts(filename, readerAndWriter);
    }

    /**
     * Saves the user accounts into the file with filename.
     *
     * Precondition:
     * - The file corresponding to filename is a file storing a mapping of username to User object.
     *
     * @param filename          The name of the file storing accounts.
     * @param readerAndWriter   The data access interface.
     */
    public void saveAccounts(String filename, FileReaderAndWriter readerAndWriter){
        appManagerAccounts.saveAccounts(filename, readerAndWriter);
    }

    /**
     * Shows the login window. Allows the user to login.
     */
    public String login(){
        return this.appManagerAccounts.login();
    }

    /**
     * Shows the start screen window.
     */
    public String showStartScreenWindow(){
        return this.appManagerPresenters.showStartScreenWindow();
    }

    /**
     * Shows the sign-up page and manages user interactions with the program for this
     * page.
     */
    public String createNewAccount() {
        return this.appManagerAccounts.createNewAccount();
    }

    /**
     * Shows the account page. Allows the user to view their account information and
     * interact with the page.
     */
    public String showAccountWindow(){
        return this.appManagerPresenters.showAccountWindow();
    }

    /**
     * Allows the user to pick a specific medicine and edit that medication's information and the times
     * to take that medication.
     */
    public String editMedicine(){
        return this.appManagerMedicine.editMedicine();
    }

    /** This method is to run the functionality of removing a medicine, from showing the menu to
     * calling the appropriate classes to remove the medicine from the account.
     */
    public String removeMedicine(){
       return this.appManagerMedicine.removeMedicine();
    }

    /**
     * Shows the add medicine page. Allows user interaction with the program so that
     * the user can add a new medicine.
     */
    public String addMedicine(){
        return this.appManagerMedicine.addMedicine();
    }

    /**
     * Adds a new prescription to the current user's prescriptions list
     */
    public String addPrescription(){
        return this.appManagerPrescription.addPrescription();
    }

    /**
     * Removes a prescription from the current user's prescriptions list
     */
    public String removePrescription(){
        return this.appManagerPrescription.removePrescription();
    }

    /**
     * Edits a prescription which user specifies
     */
    public String editPrescription(){
        return this.appManagerPrescription.editPrescription();
    }

    /**
     * Shows the set sleep times window, and allows the user to set their sleep schedule.
     * @return  The name of the next window to open.
     */
    public String setSleepTimes(){
        return this.appManagerActivitySetter.setSleepTimes();
    }

    /**
     * Shows the set meal times window, and allows the user to set their meal schedule.
     * @return  The name of the next window to open.
     */
    public String setMealTimes(){
        return this.appManagerActivitySetter.setMealTimes();
    }

    /**
     * Shows the final schedule by using managementSystemFacade to make the schedule and using the TimeTableWindow class
     * to display the final schedule. It then gets user input from TimeTableWindow and calls ViewAccountWindow.
     */
    public String showFinalSchedule() {
        Window window = windows.get("TimeTable Window");
        // Make the schedule to be displayed
        String[] schedule = managementSystemFacade.makeSchedule();

        // Update the view of the window
        window.updateFrame();

        // Display the schedule.
        if (window instanceof DisplayEntityInformation)
            ((DisplayEntityInformation) window).displayInfo(schedule);

        //Done: get user input from TimeTableWindow and call ViewAccountWindow.
        while(!window.userResponded) {
            windows.get("TimeTable Window").getUserInput();
        }

        // There is only one option, which is to take the user back to the account page.
        return "View Account Window";
    }

}
