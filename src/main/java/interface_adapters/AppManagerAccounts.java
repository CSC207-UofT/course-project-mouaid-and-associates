package interface_adapters;

import application_business_rules.FileReaderAndWriter;
import application_business_rules.ManagementSystem;

import java.util.List;
import java.util.Map;

public class AppManagerAccounts {
    /**
     * The class that takes care of all Accounts related tasks in the AppManagerFacade
     */

    private Map<String, Window> windows;
    private ManagementSystem managementSystem;

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
}
