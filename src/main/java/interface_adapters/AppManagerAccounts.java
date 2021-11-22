package interface_adapters;

import application_business_rules.FileReaderAndWriter;
import application_business_rules.ManagementSystem;

import java.util.List;
import java.util.Map;

public class AppManagerAccounts {
    /**
     * The class that takes care of setting the activity times in the AppManagerFacade
     */

    Map<String, Window> windows;
    ManagementSystem managementSystem;

    public AppManagerAccounts(Map<String, Window> windows, ManagementSystem managementSystem) {
        this.windows = windows;
        this.managementSystem = managementSystem;
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
        managementSystem.setUpAccounts(filename, readerAndWriter);
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
        managementSystem.saveAccounts(filename, readerAndWriter);
    }

    /**
     * Shows the login window. Allows the user to login.
     */
    public String login(){
        Window loginWindow = windows.get("Login Window");
        String [] input;
        boolean userIsVerified;
        do {
            input = loginWindow.getUserInput();
            userIsVerified = managementSystem.verifyUserAccount(input);

            if(!userIsVerified && loginWindow instanceof DisplayEntityInformation){
                ((DisplayEntityInformation) loginWindow).displayInfo(new String[]{
                        "Incorrect Username or Password. Please try again."});
            }

        }while (!userIsVerified);

        return "View Account Window";
    }

    /**
     * Shows the start screen window.
     */
    public String showStartScreenWindow(){

        //Done make a call to StartScreenWindow to show information and get user input
        String[] choice = windows.get("Start Screen Window").getUserInput();      // Currently, placeholder.


        if (choice[0].equals("0")){
            return "Login Window";
        } else {
            return "Create Account Window";
        }

    }

    /**
     * Shows the sign-up page and manages user interactions with the program for this
     * page.
     */
    public String createNewAccount() {
        //DONE: call CreateAccountWindow to show information and get user input
        Window createAccountWindow = windows.get("Create Account Window");
        String[] inputInfo = createAccountWindow.getUserInput();

        //DONE: obtain name, username and password
        String name = inputInfo[0];
        String username = inputInfo[1];
        String password = inputInfo[2];

        //Done: call managementSystem.createNewUser, with name and username as parameters
        managementSystem.createNewUser(name, username, password);

        //Done: call showAccountWindow()
        return "View Account Window";
    }

    /**
     * Shows the account page. Allows the user to view their account information and
     * interact with the page.
     */
    public String showAccountWindow(){
        //Done: call managementSystem.getUserInfo() to get user information.
        String[] userInfo = managementSystem.getUserInfo().toArray(new String[0]);
        Window viewAccountWindow = windows.get("View Account Window");
        List<String> prescriptionsIDS = managementSystem.getPrescriptionsNames();

        // create a new array for properly formatted strings.
        String[] formattedUserInfo = new String[userInfo.length + prescriptionsIDS.size() + 2];

        formattedUserInfo[0] = "Name: " + userInfo[0];
        formattedUserInfo[1] = "Username: " + userInfo[1];
        formattedUserInfo[2] = "List of Medicines: ";
        formattedUserInfo[userInfo.length + 1] = "List of Prescriptions: ";

        // Format the list of medicines names and add them.
        for (int i = 3; i < formattedUserInfo.length - (prescriptionsIDS.size() + 1); i++){
            formattedUserInfo[i] = (" - " + userInfo[i - 1]);
        }
        // Format the list of prescriptions IDs and add them
        int accumulator = 0;
        for(int i = userInfo.length + 2; i < formattedUserInfo.length; i++){
            formattedUserInfo[i] = (" - " + prescriptionsIDS.get(accumulator));
        }
        if (viewAccountWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) viewAccountWindow).displayInfo(formattedUserInfo);
        }


        String choice = viewAccountWindow.getUserInput()[0];

        //Done: Add conditional flow statements so that the user can select between logging out,
        //      adding medication and viewing the timetable.

        if (choice.equals("add")){
            return "Add Medicine Window";
        } else if (choice.equals("view")){
            return "TimeTable Window";
        } else if (choice.equals("edit")){
            return "Edit Medicine Window";
        }else if (choice.equals("edit pres")) {
            return "Edit Prescription Window";
        } else if (choice.equals("pres")){
            return "Add Prescription Window";
        } else if (choice.equals("remove pres")){
            return "Remove Prescription Window";
        } else if (choice.equals("remove")) {
            return "Remove Medicine Window";
        } else if (choice.equals("set sleep times")) {
            return "Set Sleep Timings Window";
        } else if (choice.equals("set meal times")){
            return "Set Meal Timings Window";
        }else {
            return "Log Out";
        }

    }
}
