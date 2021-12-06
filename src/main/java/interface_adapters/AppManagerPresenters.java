


package interface_adapters;

import application_business_rules.ManagementSystemFacade;

import java.util.List;
import java.util.Map;

public class AppManagerPresenters {
    /**
     * The class that takes care of the presenter methods in the AppManagerFacade
     */

    private Map<String, Window> windows;
    private ManagementSystemFacade managementSystemFacade;
    private AppManagerHelpers appManagerHelpers;

    public AppManagerPresenters(Map<String, Window> windows, ManagementSystemFacade managementSystemFacade) {
        this.windows = windows;
        this.managementSystemFacade = managementSystemFacade;
    }

    /**
     * Shows the start screen window.
     */
    public String showStartScreenWindow(){
        Window window = windows.get("Start Screen Window");

        // Change the view of the screen.
        window.updateFrame();
        //Done make a call to StartScreenWindow to show information and get user input
        String[] choice = new String[0];        // Initialize the variable

        // Wait until the user has actually responded.
        while (!window.userResponded) {
            choice = windows.get("Start Screen Window").getUserInput();      // Currently, placeholder.
        }

        if (choice[0].equals("0")){
            return "Login Window";
        } else {
            return "Create Account Window";
        }

    }

    /**
     * Shows the account page. Allows the user to view their account information and
     * interact with the page.
     */
    public String showAccountWindow(){
        //Done: call managementSystemFacade.getUserInfo() to get user information.
        String[] userInfo = managementSystemFacade.getUserInfo().toArray(new String[0]);
        Window viewAccountWindow = windows.get("View Account Window");
        List<String> prescriptionsIDS = managementSystemFacade.getPrescriptionsNames();

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

        // Update the frame to show the new view


        if (viewAccountWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) viewAccountWindow).displayInfo(formattedUserInfo);
        }
        viewAccountWindow.updateFrame();


        String choice = "";
        while(!viewAccountWindow.userResponded) {      // Keep asking for userInput until we get something.
            choice = viewAccountWindow.getUserInput()[0];

            // For some reason, we don't exit the loop unless I add this line.
            System.out.print("");
        }

        //Done: Add conditional flow statements so that the user can select between logging out,
        //      adding medication and viewing the timetable.

        if (choice.equals("Add Medicine")){
            return "Add Medicine Window";
        } else if (choice.equals("View Timetable")){
            return "TimeTable Window";
        } else if (choice.equals("Edit Medicine")){
            return "Edit Medicine Window";
        }else if (choice.equals("Edit Prescription")) {
            return "Edit Prescription Window";
        } else if (choice.equals("Add Prescription")){
            return "Add Prescription Window";
        } else if (choice.equals("Remove Prescription")){
            return "Remove Prescription Window";
        } else if (choice.equals("Remove Medicine")) {
            return "Remove Medicine Window";
        } else if (choice.equals("Set Sleep Times")) {
            return "Set Sleep Timings Window";
        } else if (choice.equals("Set Meal Times")){
            return "Set Meal Timings Window";
        }else {
            return "Log Out";
        }

    }
}
