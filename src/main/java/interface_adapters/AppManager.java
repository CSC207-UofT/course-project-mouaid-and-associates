package interface_adapters;

import application_business_rules.ManagementSystem;

import java.util.*;

public class AppManager {
    /** This is the main class that runs the entire app.
     *
     * Instance Attributes:
     * - managementSystem: An input and output boundary between the interface adapters like AppManager
     *                     and use case interactors like UserManager and MedicineManager.
     * - windows: A mapping of a string name for a window to a Window object.
     * - accounts: A mapping of usernames to passwords. Will likely be changed to be stored in a database
     *             in the future.
     * - DAYS: A string array of the days of the week.
     *
     * Representation Invariants:
     * - The keys of windows are {"Login Window", "Create Account Window", "Start Screen Window",
     *                            "TimeTable Window", "View Account Window", "Add Medicine Window",
     *                            "Edit Medicine Window", "Remove Medicine Window",
     *                            "Choose Medicine To Edit Window", "Set Sleep Timings Window"}
     *
     *                            * More may be added in the future.
     *
     */

    private ManagementSystem managementSystem;
    private Map<String, Window> windows;
    private Map<String, String> accounts;
    private static final String[] DAYS = new String[]{"Monday", "Tuesday", "Wednesday",
    "Thursday", "Friday", "Saturday", "Sunday"};

    public AppManager(){
        managementSystem = new ManagementSystem();
        accounts = new HashMap<>();
    }

    /** The main runner of the program. It starts of the app.
     *
     * @param windows   All the windows to be used by the app.
     */
    public void run(Map<String, Window> windows){
        // Creates an array for Window objects. Once the program starts, we will only have a set
        // number of windows, thus an array makes sense.
        this.windows = windows;

        // Create a variable that sends us to the next window.
        String next_window;
        //Done: Add a call to Start Screen Window
        next_window = showStartScreenWindow();

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
                case "Remove Medicine Window":
                    next_window = removeMedicine();
                    break;
                case "Set Sleep Timings Window":
                    next_window = setSleepTimes();
                    break;
                case "Set Meal Timings Window":
                    next_window = setMealTimes();
                    break;
                default:
                    next_window = "Log Out";
                    break;
            }
        }

        logOut();
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
     * Shows the login window. Allows the user to login.
     */
    public String login(){
        //FUTURE TODO: Fill this out later
        return "View Account Window";
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
        //DONE: Add username and password to accounts.

        this.accounts.put(username, password);

        //Done: call managementSystem.createNewUser, with name and username as parameters
        managementSystem.createNewUser(name, username);

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

        // create a new array for properly formatted strings.
        String[] formattedUserInfo = new String[userInfo.length + 1];

        formattedUserInfo[0] = "Name: " + userInfo[0];
        formattedUserInfo[1] = "Username: " + userInfo[1];
        formattedUserInfo[2] = "List of Medicines: ";

        // Format the list of medicines names and add them.
        for (int i = 3; i < formattedUserInfo.length; i++){
            formattedUserInfo[i] = (" - " + userInfo[i - 1]);
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
        } else if (choice.equals("remove")) {
            return "Remove Medicine Window";
        } else if (choice.equals("set sleep times")) {
            return "Set Sleep Timings Window";
        } else if (choice.equals("Set Meal Timings Window")){
            return "Set Meal Timings Window";
        }else {
            return "Log Out";
        }

    }

    /**
     *
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
        String[] medList = getFormattedList("List of medicines to choose from: ", bigList,
                2, bigList.length);
        // First, instantiate a variable to store the name of the medicine the user would like to remove.
        String medName;
        String[] medInfo;
        String[] changes; // Represents the changes the user wants to make.
        List<Map<String, Double>> newTimes = new ArrayList<>();

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
        if (changes.length > 4) {
            formatTimes(changes, changes[4], changes[5], newTimes);
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
        String[] infoToPrint = getFormattedList("The list of medicines to pick from: ", userInfo, 2,
                userInfo.length);

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

        String name = data[0];
        String methodOfAdmin = data[1];
        int amount;
        String extraInstruct = data[3];
        String wOrD = data[4]; // Stores frequency. Weekly/Daily
        String startDay = data[5];
        List<Map<String, Double>> times = new ArrayList<>();

        // In case the user decided to not enter a proper value.
        try {
            amount = Integer.parseInt(data[2]);
        } catch (NumberFormatException e) {
            amount = -1;
        }

        formatTimes(data, wOrD, startDay, times);

        //Done: call managementSystem.addNewMedicine() and pass in this information.
        managementSystem.addNewMedicine(name, amount, methodOfAdmin, extraInstruct, times);

        //Done: call showAccountWindow
        return "View Account Window";
    }

    /**
     * Shows the set sleep times window, and allows the user to set their sleep schedule.
     * @return  The name of the next window to open.
     */
    public String setSleepTimes(){
        // Call SetSleepTimingsWindow to display the fields to enter the sleep and wakeup times
        Window setSleepTimingsWindow = windows.get("Set Sleep Timings Window");

        String[] stringTimings = setSleepTimingsWindow.getUserInput();

        Double sleepTime = Double.parseDouble(stringTimings[0]);
        Double wakeUpTime = Double.parseDouble(stringTimings[1]);

        List<Double> times = new ArrayList<>();
        times.add(sleepTime);
        times.add(wakeUpTime);

        this.managementSystem.setSleepAndWakeUpTimes(times);

        // Return to the account page.
        return "View Account Window";
    }

    /**
     * Shows the set meal times window, and allows the user to set their meal schedule.
     * @return  The name of the next window to open.
     */
    public String setMealTimes(){
        // Call SetMealTimingsWindow to display the fields to enter the Meal times
        Window setMealTimingsWindow = windows.get("Set Meal Timings Window");

        String[] stringTimings = setMealTimingsWindow.getUserInput();
        List<Double> times = new ArrayList<>();

        for (String timings: stringTimings){
            times.add(Double.valueOf(timings));
        }

        this.managementSystem.setMealTimes(times);

        // Return to the account page.
        return "View Account Window";
    }

    /**
     * Formats the times returned by user input.
     * @param data      The user input
     * @param wOrD      A variable determining weekly OR daily.
     * @param startDay  The day the first time occurs.
     * @param times     The mapping on which we will save the formatted times.
     */
    private void formatTimes(String[] data, String wOrD, String startDay, List<Map<String, Double>> times) {
        for(int i = 0; i < (data.length - 6); i++) {
            if (wOrD.equals("weekly")) {
                Map<String, Double> map = new HashMap<>();
                map.put(DAYS[Integer.parseInt(startDay) - 1], Double.parseDouble(data[i + 6]));
                times.add(map);
            } else {
                for (String day : DAYS) {
                    Map<String, Double> map2 = new HashMap<>();
                    map2.put(day, Double.parseDouble(data[i + 6]));
                    times.add(map2);
                }
            }

        }
    }

    /**
     * Shows the final schedule by using managementSystem to make the schedule and using the TimeTableWindow class
     * to display the final schedule. It then gets user input from TimeTableWindow and calls ViewAccountWindow.
     */
    public String showFinalSchedule() {
        Window window = windows.get("TimeTable Window");
        //Done: call managementSystem.makeSchedule
        String[] schedule = new String[]{managementSystem.makeSchedule()};

        if (window instanceof DisplayEntityInformation)
            ((DisplayEntityInformation) window).displayInfo(schedule);

        //Done: get user input from TimeTableWindow and call ViewAccountWindow.
        windows.get("TimeTable Window").getUserInput();

        // For now, we only have one option, which is to take the user back to the account page.

        return "View Account Window";
    }

    /**
     * Allows the user to log out. Since at the end of every method, another method is called,
     * and none of the methods return anything, calling an empty method ends the program, since there
     * are no more calls to be made.
     */
    public void logOut(){}

    /**
     * A private helper method to help format lists you need to print out to the command line.
     * @param title       The title of the list.
     * @param list        The list of items to be printed
     * @param startIndex  The index from which we start including items from list.
     * @param endIndex    The index directly after the last item we want to include from the list.
     * @return            A list of formatted things that can be printed out on the command line.
     */
    private String[] getFormattedList(String title, String[] list, int startIndex, int endIndex){
        // First, start with a list of the correct length
        String[] formattedList = new String[endIndex - startIndex + 1];
        formattedList[0] = title;

        for (int i = 1; i < formattedList.length; i++){
            formattedList[i] = " - " + list[i + startIndex - 1];
        }

        return formattedList;
    }
}
