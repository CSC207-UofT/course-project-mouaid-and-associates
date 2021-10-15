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

        //Done make a call to StartScreenWindow to show information and get user input
        String[] choice = windows.get("Start Screen Window").getUserInput();      // Currently, placeholder.


        if (choice[0].equals("1")){
            login();
        } else {
            createNewAccount();
        }

    }

    public void login(){
        //TODO: FUTURE: Fill this out later
    }

    public void createNewAccount() {
        //DONE: call CreateAccountWindow to show information and get user input
        Window createAccountWindow = windows.get("Create Account Window");
        String[] inputInfo = createAccountWindow.getUserInput();

        //DONE: obtain name, username and password
        String name = inputInfo[0];
        String username = inputInfo[1];
        String password = inputInfo[2];
        //DONE: Add username and password to accounts.

        this.accounts.put(username, password);

        //TODO: call managementSystem.createNewUser, with name and username as parameters
//        managementSystem.createNewUser(name, username);

        //Done: call showAccountWindow()
        showAccountWindow();
    }
    public void showAccountWindow(){
        //Done: call managementSystem.getUserInfo() to get user information.
        String[] userInfo = managementSystem.getUserInfo().toArray(new String[0]);
        Window viewAccountWindow = windows.get("View Account Window");

        // create a new array for properly formatted strings.
        String[] formattedUserInfo = new String[userInfo.length + 1];

        formattedUserInfo[0] = "Name" + userInfo[0];
        formattedUserInfo[1] = "Username" + userInfo[1];
        formattedUserInfo[2] = "List of Medicines: ";

        // Format the list of medicines names and add them.
        for (int i = 3; i < formattedUserInfo.length; i++){
            formattedUserInfo[i] = (" - " + userInfo[i - 1]);
        }

        if (viewAccountWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) viewAccountWindow).displayInfo(formattedUserInfo);
        }


        String choice = viewAccountWindow.getUserInput()[0];     // place holder for user's choice.

//        choice = (int) viewAccountWindow.getUserInput();

        //Done: Add conditional flow statements so that the user can select between logging out,
        //      adding medication and viewing the timetable.

        switch (choice) {
            case "add" -> addMedicine();
            case "logout" -> logOut();
            case "view timetable" -> showFinalSchedule();
        }

    }

    public void addMedicine(){
        //Done: call AddMedicineWindow to display the fields to enter data about the medicine
        Window addMedicineWindow = windows.get("Add Medicine Window");

        //TODO: gets user input on the name, and type of medicine. Also the method of administration,
        //      extra instructions, as well as times to take the medicine.

        String[] data = addMedicineWindow.getUserInput();

        String name = data[0];
        String methodOfAdmin = data[1];
        int amount = Integer.parseInt(data[2]);
        String extraInstruct = data[3];
        HashMap<String, Double> times = new HashMap<>();
        for(int i = 0; i < (data.length - 4); i++){
            times.put("Tuesday", Double.parseDouble(data[i + 4]);

        }

        //TODO: Add a method that properly formats times from user input.

        //Done: call managementSystem.addNewMedicine() and pass in this information.
//        managementSystem.addNewMedicine(name, amount, methodOfAdmin, extraInstruct, times);
        //Done: call showAccountWindow
        showAccountWindow();
    }

    public void showFinalSchedule() {
        //Done: call managementSystem.makeSchedule
        String[] schedule = new String[]{managementSystem.makeSchedule()};

        if (window instanceof DisplayEntityInformation)
            ((DisplayEntityInformation) window).displayInfo(schedule);

        //Done: get user input from TimeTableWindow and call ViewAccountWindow.
        windows.get("TimeTable Window").getUserInput();

        // For now, we only have one option, which is to take the user back to the account page.

        showAccountWindow();
    }

    public void logOut() {}

}
