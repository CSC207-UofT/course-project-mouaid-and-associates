package interface_adapters;

import application_business_rules.ManagementSystem;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Random;
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
     *                            "Choose Medicine To Edit Window"}
     *
     *                            * More may be added in the future.
     *
     */

    private ManagementSystem managementSystem;
    private Map<String, Window> windows;
    private Map<String, String> accounts;
    private static final String[] DAYS = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday",
    "Thursday", "Friday", "Saturday"};

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

        //Done: Add a call to Start Screen Window
        showStartScreenWindow();

    }

    /**
     * Shows the start screen window.
     */
    public void showStartScreenWindow(){

        //Done make a call to StartScreenWindow to show information and get user input
        String[] choice = windows.get("Start Screen Window").getUserInput();      // Currently, placeholder.


        if (choice[0].equals("0")){
            login();
        } else {
            createNewAccount();
        }

    }

    /**
     * Shows the login window. Allows the user to login.
     */
    public void login(){
        //FUTURE TODO: Fill this out later
    }

    /**
     * Shows the sign-up page and manages user interactions with the program for this
     * page.
     */
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

        //Done: call managementSystem.createNewUser, with name and username as parameters
        managementSystem.createNewUser(name, username);

        //Done: call showAccountWindow()
        showAccountWindow();
    }

    /**
     * Shows the account page. Allows the user to view their account information and
     * interact with the page.
     */
    public void showAccountWindow(){
        //Done: call managementSystem.getUserInfo() to get user information.
        String[] userInfo = managementSystem.getUserInfo().toArray(new String[0]);
        Window viewAccountWindow = windows.get("View Account Window");
        List<Integer> prescriptionsIDS = managementSystem.getPrescriptionsIDs();

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
            addMedicine();
        } else if (choice.equals("logout")){
            logOut();
        } else if (choice.equals("view")){
            showFinalSchedule();
        } else if (choice.equals("edit")){
            editMedicine();
        } else if (choice.equals("remove")){
            removeMedicine();
        } else if (choice.equals("pres")){
            addPrescription();
        } else if (choice.equals("remove pres")){
            removePrescription();
        }

    }

    /**
     *
     */
    public void editMedicine(){
        // First instantiate a window for EditMedicineWindow
        Window editMedicineWindow = windows.get("Edit Medicine Window");
        // Next, instantiate a window for ChooseMedicineToEditWindow
        Window chooseMedicineToEditWindow = windows.get("Edit Medicine To Edit Window");

        // Get a list of medicine names, like for removeMedicine
        // First get a list of user information
        String[] bigList = managementSystem.getUserInfo().toArray(new String[0]);

        // Isolate and get the list of medicine names
        String[] medList = getFormattedList("List of medicines to choose from: ", bigList,
                2, bigList.length);

        // First, instantiate a variable to store the name of the medicine the user would like to remove.
        String medName;
        String[] medInfo;

        // Check if ChooseMedicineToEditWindow is an instance of DisplayEntityInformation to print
        // out the list of medicine names, like in removeMedicine()
        if (chooseMedicineToEditWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) chooseMedicineToEditWindow).displayInfo(medList);
        }

        //TODO: Verify the name is a proper one in ChooseMedicineToEditWindow
        medName = chooseMedicineToEditWindow.getUserInput()[0];

        medInfo = managementSystem.getMedicineInfo(medName);

        if (editMedicineWindow instanceof DisplayEntityInformation){
            ((DisplayEntityInformation) editMedicineWindow).displayInfo(medInfo);
        }

        //TODO: Get user input as an array, with each index representing a different variable
        //      If you want, you can change the format to a map later in the program. Add methods to
        //      medicineManager to set Medicine attributes using the values in the list. Add the supporting
        //      method to userManager to be able to call medicine manager. To set the times, call
        //      schedule manager. You can pass schedule manager as a parameter into user manager or call
        //      directly in management system.
        //TODO: Call EditMedicineWindow and get user input on which aspects to change.
        //      Return an array with the same order as the addMedicine parameters.
        //TODO: Call ManagementSystem to update this medicine information.
        //TODO: add the necessary methods in the necessary classes.
    }



    /** This method is to run the functionality of removing a medicine, from showing the menu to
     * calling the appropriate classes to remove the medicine from the account.
     */
    public void removeMedicine(){
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
        showAccountWindow();
    }

    /**
     * Shows the add medicine page. Allows user interaction with the program so that
     * the user can add a new medicine.
     */
    public void addMedicine(){
        //Done: call AddMedicineWindow to display the fields to enter data about the medicine
        Window addMedicineWindow = windows.get("Add Medicine Window");

        String[] data = addMedicineWindow.getUserInput();

        addMedicineHelper(data);

        //Done: call showAccountWindow
        showAccountWindow();
    }

    /**
     * Adds a new prescription to the current user's prescriptions list
     */
    public void addPrescription(){
        Window addPrescriptionWindow = windows.get("Add Prescription Window");
        List<String[]> data = ((PrescriptionWindow) addPrescriptionWindow).getUserPrescriptionInput();
        List<String> medicinesNames = new ArrayList<>();
        for(String[] medicine : data){
            addMedicineHelper(medicine);
            medicinesNames.add(medicine[0]);
        }
        managementSystem.addNewPrescription(medicinesNames, generateID());
        showAccountWindow();

    }

    /**
     * Generates an ID for a prescription
     * @return the ID
     */
    private int generateID() {
        Random rand = new Random();
        while(true){
            int id = rand.nextInt(10000);
            if(managementSystem.IDChecker(id)){
            }
            else{
                return id;
            }

        }

    }

    /**
     * Removes a prescription from the current user's prescriptions list
     */
    public void removePrescription(){
        Window removePrescriptionWindow = windows.get("Remove Prescription Window");
        String[] data = removePrescriptionWindow.getUserInput();
        managementSystem.removePrescription(data[0]);
        showAccountWindow();
    }

    private void addMedicineHelper(String[] data) {
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

        //Done: call managementSystem.addNewMedicine() and pass in this information.
        managementSystem.addNewMedicine(name, amount, methodOfAdmin, extraInstruct, times);
    }
    /**
     * Shows the final schedule by using managementSystem to make the schedule and using the TimeTableWindow class
     * to display the final schedule. It then gets user input from TimeTableWindow and calls ViewAccountWindow.
     */
    public void showFinalSchedule() {
        Window window = windows.get("TimeTable Window");
        //Done: call managementSystem.makeSchedule
        String[] schedule = new String[]{managementSystem.makeSchedule()};

        if (window instanceof DisplayEntityInformation)
            ((DisplayEntityInformation) window).displayInfo(schedule);

        //Done: get user input from TimeTableWindow and call ViewAccountWindow.
        windows.get("TimeTable Window").getUserInput();

        // For now, we only have one option, which is to take the user back to the account page.

        showAccountWindow();
    }
    public List<Integer> getPrescriptionID(){
        return managementSystem.getPrescriptionsIDs();
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
