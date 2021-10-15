package interface_adapters;

import application_business_rules.ManagementSystem;

import java.util.HashMap;
import java.util.Map;

public class AppManager {
    /** This is the main class that runs the entire app.
     *
     * Instance Attributes:
     * - managementSystem: An input and output boundary between the interface adapters like AppManager
     *                     and use case interactors like UserManager and MedicineManager.
     * - windows: A mapping of a string name for a window to a Window object.
     * - accounts: A mapping of usernames to passwords. Will likely be changed to be stored in a database
     *             in the future.
     *
     * Representation Invariants:
     * - The keys of windows are {"Login Window", "Create Account Window", "Start Screen Window",
     *                            "TimeTable Window", "View Account Window", "Add Medicine Window",
     *                            "Edit Medicine Window", "Remove Medicine Window" }
     *
     *                            * More may be added in the future.
     *
     */

    private ManagementSystem managementSystem;
    private Map<String, Window> windows;
    private Map<String, String> accounts;


    public AppManager(){
        managementSystem = new ManagementSystem();
        accounts = new HashMap<>();
    }

    public void run(Map<String, Window> windows){
        // Creates an array for Window objects. Once the program starts, we will only have a set
        // number of windows, thus an array makes sense.
        this.windows = windows;

        //TODO: Add a call to Start Screen Window
    }

    public void showStartScreenWindow(){

        //TODO make a call to StartScreenWindow to show information and get user input

        //TODO make a call to createNewAccount
    }

    public void createNewAccount(){}
        //TODO: call CreateAccountWindow to show information and get user input
        //TODO: obtain name, username and password
        //TODO: Add username and password to accounts.
        //TODO: call managementSystem.createNewUser, with name and username as parameters
        //TODO: call showAccountWindow()

    public void showAccountWindow(){
        //TODO: call managementSystem.getUserInfo() to get user information.
        //TODO: call ViewAccountWindow and pass the user information as parameters so that
        //      they can display it.
        //TODO: Add conditional flow statements so that the user can select between logging out,
        //      adding medication and viewing the timetable.
        //TODO: get user input from ViewAccountWindow and call addMedicine()
        //TODO: get user input and call logOut()
        //TODO: get user input and call showFinalSchedule.
    }

    public void addMedicine(){
        //TODO: call AddMedicineWindow to display the fields to enter data about the medicine
        //TODO: gets user input on the name, and type of medicine. Also the method of administration,
        //      extra instructions, as well as times to take the medicine.
        //TODO: call managementSystem.addNewMedicine() and pass in this information.
        //TODO: call showAccountWindow
    }

    public void showFinalSchedule(){}
        //TODO: call managementSystem.makeSchedule
        //TODO: get the schedule and pass it into as a parameter for TimeTableWindow.showOptions() or whatever
        //      method is used to display information.

        //TODO: get user input from TimeTableWindow and call ViewAccountWindow.

    public void logOut(){}

        //TODO: Do something to end run() or maybe go back to login window.

}
