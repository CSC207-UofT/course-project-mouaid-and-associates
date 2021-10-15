package frameworks_and_drivers;

import interface_adapters.Window;

import java.io.Console;

public class LoginWindow implements Window {

    Console cnsl = System.console();
    //The user logs in.
    @Override
    public String[] getUserInput() {
        String[] returnList = new String[2];
        String username = cnsl.readLine("Username:");
        String password = cnsl.readLine("Password:");
        returnList[0] = username;
        returnList[1] = password;

        return returnList;
        }

    }
