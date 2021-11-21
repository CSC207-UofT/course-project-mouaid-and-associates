package interface_adapters;

import application_business_rules.FileReaderAndWriter;
import application_business_rules.ManagementSystem;

import java.time.LocalDateTime;
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
     *                            "Choose Medicine To Edit Window", "Set Sleep Timings Window",
     *                            "Remove Prescription Window", "Add Prescription Window" }
     *
     *                            * More may be added in the future.
     *
     */

    private ManagementSystem managementSystem;
    private Map<String, Window> windows;
    private static final String[] DAYS = new String[]{"Monday", "Tuesday", "Wednesday",
    "Thursday", "Friday", "Saturday", "Sunday"};

    public AppManager(){
        managementSystem = new ManagementSystem();
    }

    /** The main runner of the program. It starts of the app.
     *
     * @param windows   All the windows to be used by the app.
     */
    public void run(Map<String, Window> windows, FileReaderAndWriter readerAndWriter, String accountFile){
        // Creates an array for Window objects. Once the program starts, we will only have a set
        // number of windows, thus an array makes sense.
        this.windows = windows;

        // Set up the user accounts from the file
        setUpAccounts(accountFile, readerAndWriter);

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

    /**
     * Allows the user to pick a specific medicine and edit that medication's information and the times
     * to take that medication.
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
        List<LocalDateTime> newTimes = new ArrayList<>();

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
        if (changes.length > 5) {
            formatTimes(changes, changes[5], changes[6], changes[7], newTimes);
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

        addMedicineHelper(data);

        //Done: call showAccountWindow
        return "View Account Window";
    }

    /**
     * Adds a new prescription to the current user's prescriptions list
     */
    public String addPrescription(){
        Window addPrescriptionWindow = windows.get("Add Prescription Window");
        List<String[]> data = ((PrescriptionWindow) addPrescriptionWindow).getUserPrescriptionInput();
        List<String> medicinesNames = new ArrayList<>();
        for(String[] medicine : data){
            if(medicine.length > 1){
                addMedicineHelper(medicine);
                medicinesNames.add(medicine[0]);
            }
        }
        managementSystem.addNewPrescription(medicinesNames, data.get(0)[0]);
        return "View Account Window";

    }

    /**
     * Removes a prescription from the current user's prescriptions list
     */
    public String removePrescription(){
        Window removePrescriptionWindow = windows.get("Remove Prescription Window");
        String[] data = removePrescriptionWindow.getUserInput();
        managementSystem.removePrescription(data[0]);
        return "View Account Window";
    }

    /**
     * Edits a prescription which user specifies
     */
    public String editPrescription(){
        Window editPrescriptionWindow = windows.get("Edit Prescription Window");
        Window choosePrescriptionToEditWindow = windows.get("Choose Prescription To Edit Window");
        String[] prescriptions = managementSystem.getPrescriptions();
        String[] presList = getFormattedList("List of prescriptions to choose from: ", prescriptions,
                0, prescriptions.length);

        if (choosePrescriptionToEditWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) choosePrescriptionToEditWindow).displayInfo(presList);
        }
        String presName = choosePrescriptionToEditWindow.getUserInput()[0];
        String[] change = editPrescriptionWindow.getUserInput();
        if(!change[0].equals("")){
            managementSystem.changePrescriptionName(presName, change[0]);
        }else if(!change[1].equals("")){
            managementSystem.removeMedicineFromPres(presName, change[1]);
            String[] medToRemove = new String[1];
            medToRemove[0] = change[1];
            managementSystem.removeMedicines(medToRemove);
        }else if(!change[2].equals("")){
            Window addMedicineWindow = windows.get("Add Medicine Window");
            String[] data = addMedicineWindow.getUserInput();
            addMedicineHelper(data);
            managementSystem.addMedicineToPres(presName, data[0]);
        }
        return "View Account Window";
    }
    private void addMedicineHelper(String[] data) {
        String name = data[0];
        String methodOfAdmin = data[1];
        String unitOfMeasurement = data[2];
        int amount;
        String extraInstruct = data[4];
        String wOrD = data[5]; // Stores frequency. Weekly/Daily
        String startDay = data[6];
        String startMonth = data[7];
        List<LocalDateTime> times = new ArrayList<>();

        // In case the user decided to not enter a proper value.
        try {
            amount = Integer.parseInt(data[3]);
        } catch (NumberFormatException e) {
            amount = -1;
        }

        formatTimes(data, wOrD, startDay, startMonth, times);

        //Done: call managementSystem.addNewMedicine() and pass in this information.
        managementSystem.addNewMedicine(name, amount, unitOfMeasurement, methodOfAdmin, extraInstruct, times);
    }

    /**
     * Shows the set sleep times window, and allows the user to set their sleep schedule.
     * @return  The name of the next window to open.
     */
    public String setSleepTimes(){
        // Call SetSleepTimingsWindow to display the fields to enter the sleep and wakeup times
        Window setSleepTimingsWindow = windows.get("Set Sleep Timings Window");

        String[] stringTimings = setSleepTimingsWindow.getUserInput();

        String sleepTime = stringTimings[0];
        String wakeUpTime = stringTimings[1];

        List<String> times = new ArrayList<>();
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
        List<String> times = new ArrayList<>();

        for (String timings: stringTimings){
            times.add(timings);
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
    private void formatTimes(String[] data, String wOrD, String startDay, String startMonth, List<LocalDateTime> times) {
        for(int i = 0; i < (data.length - 8); i++) {
            if (startDay.length() == 1){
                startDay = "0" + startDay;
            }
            if (wOrD.equals("weekly")) {
                times.add(LocalDateTime.parse("2021-" + startMonth + "-" + startDay + "T" +  data[i + 8]));
            } else {
                for (int j = 0; j <8; j++) {
                    startDay = Integer.toString(Integer.parseInt(startDay) + 1);
                    if (startDay.length() == 1){
                        startDay = "0" + startDay;
                    }
                    times.add(LocalDateTime.parse("2021-" + startMonth + "-" + startDay + "T" +  data[i + 8]));
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
    public List<String> getPrescriptionNames(){
        return managementSystem.getPrescriptionsNames();
    }

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
