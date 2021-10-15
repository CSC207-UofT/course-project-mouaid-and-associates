package interface_adapters;

import application_business_rules.ManagementSystem;

import java.util.HashMap;
import java.util.List;
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

        //Done: Add a call to Start Screen Window
        showStartScreenWindow();

    }

    public void showStartScreenWindow(){

        //TODO make a call to StartScreenWindow to show information and get user input
        windows.get("Start Screen Window").showOptions();       // Currently, a placeholder
        windows.get("Start Screen Window").getUserInput();      // Currently, placeholder.

        // FOR FUTURE: Add a control flow section so that we can also go to login window.
        //Done make a call to createNewAccount
        createNewAccount();
    }

    public void createNewAccount() {
        //TODO: call CreateAccountWindow to show information and get user input
        Window createAccountWindow = windows.get("Create Account Window");     // Place holder.

        //TODO: obtain name, username and password
//        String name;
//        String username;
//        String password;
//        name, username, password = createAccountWindow.getUserInput();        // Place holder.

        //TODO: Add username and password to accounts.
//        accounts.put(username, password);

        //TODO: call managementSystem.createNewUser, with name and username as parameters
//        managementSystem.createNewUser(name, username);

        //Done: call showAccountWindow()
        showAccountWindow();
    }
    public void showAccountWindow(){
        //Done: call managementSystem.getUserInfo() to get user information.
        List<String> userInfo = managementSystem.getUserInfo();
        Window viewAccountWindow = windows.get("View Account Window");

        //TODO: call ViewAccountWindow and pass the user information as parameters so that
        //      they can display it.

//        viewAccountWindow.showOptions(userInfo);

        int choice;     // place holder for user's choice.

//        choice = (int) viewAccountWindow.getUserInput();

        //Done: Add conditional flow statements so that the user can select between logging out,
        //      adding medication and viewing the timetable.

        if (choice == 1){
            addMedicine();
        } else if (choice == 2) {
            logOut();
        } else {
            showFinalSchedule();
        }

    }

    public void addMedicine(){
        //Done: call AddMedicineWindow to display the fields to enter data about the medicine
        Window addMedicineWindow = windows.get("Add Medicine Window");

        //TODO: gets user input on the name, and type of medicine. Also the method of administration,
        //      extra instructions, as well as times to take the medicine.
        String name;
        String type;
        String methodOfAdmin;
        String extraInstruct;
        List<Map<String, Double>> times;

        //TODO: Add a method that properly formats times from user input.

        //Done: call managementSystem.addNewMedicine() and pass in this information.
//        managementSystem.addNewMedicine(name, amount, methodOfAdmin, extraInstruct, times);
        //Done: call showAccountWindow
        showAccountWindow();
    }

    public void showFinalSchedule() {
        //Done: call managementSystem.makeSchedule
        String schedule = managementSystem.makeSchedule();
        //TODO: get the schedule and pass it into as a parameter for TimeTableWindow.showOptions() or whatever
        //      method is used to display information.
//        windows.get("TimeTable Window").showOptions(schedule);

        //TODO: get user input from TimeTableWindow and call ViewAccountWindow.
//        windows.get("TimeTable Window").getUserInput()

//        showAccountWindow();
    }
    public void logOut() {}

}
