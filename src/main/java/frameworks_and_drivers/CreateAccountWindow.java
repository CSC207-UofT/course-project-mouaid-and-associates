package frameworks_and_drivers;

import interface_adapters.Window;

import java.io.Console;

public class CreateAccountWindow implements Window {

    Console cnsl = System.console();
    //Creates an account for a user.
    @Override
    public String[] getUserInput() {
        String name = cnsl.readLine("Name:");
        String username = cnsl.readLine("Username:");
        String password = cnsl.readLine("Password:");

        String[] returnList = new String[3];
        returnList[0] = name;
        returnList[1] = username;
        returnList[2] = password;

        return returnList;

    }

}
