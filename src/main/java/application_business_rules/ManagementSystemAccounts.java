package application_business_rules;

import entities.PrescriptionMedicine;
import entities.User;

import java.util.*;

public class ManagementSystemAccounts {
    /**
     * The class that takes care of all Accounts related tasks in the ManagementSystemFacade
     */

    private HashMap<String, User> accounts;
    private UserManager userManager;

    /**
     * Creates a new ManagementSystemFacade instance. Also
     * Creates a new UserManager and ScheduleManager.
     */
    public ManagementSystemAccounts(UserManager userManager, HashMap<String, User> accounts){
        this.userManager = userManager;
        this.accounts = accounts;
    }


    public Map<String, User> getAccounts(){
        return this.accounts;
    }
    /**
     * Creates a new user instance and stores it in the accounts
     * @param name The name of the user
     * @param username the username that the user will use to log in
     */
    public void createNewUser(String name, String username, String password){
        this.accounts.put(username, this.userManager.addNewUser(name, username, password));
    }

    /**
     * This method sets up the user accounts prior to program starting.
     * @param filename          The file containing user accounts
     * @param readerAndWriter   The data access interface used to read the file.
     */
    public void setUpAccounts(String filename, FileReaderAndWriter readerAndWriter){
        // Here we create the objects from the file.
        Map<String, Object> readObjects = readerAndWriter.read(filename);
        this.accounts = new HashMap<>();
        if (Objects.isNull(readObjects)){
            readObjects = new HashMap<>();
        }
        // If there are no accounts in the file, then we will have an empty hashmap.
        if (!readObjects.isEmpty()) {
            for (String username : readObjects.keySet()) {
                this.accounts.put(username, userManager.createNewUser(readObjects.get(username)));
            }
        }
    }


    /**
     * Saves the user accounts to the file with filename.
     * @param filename          The file we are saving user accounts to.
     * @param readerAndWriter   The data access interface used to write into the file.
     */
    public void saveAccounts(String filename, FileReaderAndWriter readerAndWriter){
        //Save the accounts back into the file.
        readerAndWriter.write(filename, accounts);
    }

    /**
     *
     * @param input The input that the user gave
     * @return Whether or not the input the user gave is for an account
     *
     * Preconditions:
     *  - length(input) == 2
     */
    public boolean verifyUserAccount(String[] input){
        String username = input[0];
        String password = input[1];
        if (!accounts.containsKey(username)){
            return false;
        } else if (accounts.get(username).getPassword().equals(password)){
            userManager.setUser(accounts.get(username));
            return true;
        }
        else {
            return false;
        }

    }
    /**
     * Gets the info of the user using the username that the user uses
     * @return returns a list that contains the user's username, name and list of medicines (names only).
     */
    public List<String> getUserInfo(){
        List<String> userInfo = new ArrayList<>();
        userInfo.add(userManager.getName());
        userInfo.add(userManager.getUserName());

        // Get specifically the names of the medicine.
        Collections.addAll(userInfo, userManager.getMedicineNames());

        return userInfo;
    }
}
