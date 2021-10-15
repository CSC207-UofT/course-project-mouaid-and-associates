package interface_adapters;

import java.io.Console;

public class LoginWindow implements Window{

    Console cnsl = System.console();
    //The user logs in.
    @Override
    public String getUserInput() {
        String username = cnsl.readLine("Username:");
        String password = cnsl.readLine("Password:");

        return username + " " + password;
        }

    }
